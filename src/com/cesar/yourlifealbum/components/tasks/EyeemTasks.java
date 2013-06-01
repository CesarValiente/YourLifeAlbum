package com.cesar.yourlifealbum.components.tasks;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.application.ClassWiring;
import com.cesar.yourlifealbum.data.db.datasources.PhotoDataSource;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.managers.SharedPreferencesManager;
import com.cesar.yourlifealbum.utils.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EyeemTasks {

    public class GetAllPhotos extends AsyncTask<Void, Void, List<Photo>> {

        private final String CLASS_NAME = GetAllPhotos.class.getSimpleName();

        private Context mContext;
        private GetAllPhotosListener mListener;

        public GetAllPhotos(final Context context,
                final GetAllPhotosListener listener) {
            mContext = context;
            mListener = listener;
        }

        @Override
        protected List<Photo> doInBackground(final Void... params) {

            List<Photo> photoList = new ArrayList<Photo>();

            SharedPreferencesManager sharedPrefsManager = ClassWiring
                    .getSharedPreferencesManager();

            if (sharedPrefsManager.isFirstTimeLaunched()) {
                Log.d(CLASS_NAME, "loading from the file");
                photoList = requestPhotos();
                sharedPrefsManager.markAsFirstTimeLaunched();
                // Save data in the ddbb
                initDB(photoList);
            } else {
                Log.d(CLASS_NAME, "loading from the ddbb");
                PhotoDataSource photoDataSource = new PhotoDataSource(mContext);
                photoDataSource.open();
                // To avoid the non possible generic type casting
                // http://
                // docs.oracle.com/javase/tutorial/java/generics/subtyping.html
                photoList = (ArrayList<Photo>) (List<?>) photoDataSource
                        .getAll();
                photoDataSource.close();
            }
            return photoList;
        }

        @Override
        protected void onPostExecute(final List<Photo> photoList) {
            mListener.getAllPhotosCallback(photoList);
        }

        /**
         * Requests a new track using a request http petition and parsing the
         * received data.
         * 
         * @return {@link List<Song>}
         */
        private List<Photo> requestPhotos() {

            int tries = 3;
            List<Photo> photoList = null;

            while (photoList == null && tries > 0) {
                photoList = doRequest();
                tries--;
            }

            return photoList;
        }

        /**
         * Creates and make the request to get a {@link ArrayList<Song>}
         * 
         * @return
         */
        private List<Photo> doRequest() {

            String request = createRequestUrl();

            Log.d(CLASS_NAME, "request: " + request);

            // Request and gets the response
            String response = ClassWiring.getConnectionManager().doRequestGet(
                    request);

            Log.d(CLASS_NAME, "Response: " + response);

            return parseData(response);
        }

        /**
         * Creates the request specifying the number of {@link Song} we want
         * 
         * @return
         */
        private String createRequestUrl() {

            StringBuilder builder = new StringBuilder();
            builder.append(AppConstants.Eyeem.API_URL)
                    .append(AppConstants.Eyeem.GET_ALL_PHOTOS)
                    .append(AppConstants.Eyeem.LIMIT_PHOTOS)
                    .append(AppConstants.Eyeem.ACCESS_TOKEN_PARAM)
                    .append(AppConstants.Eyeem.ACCESS_TOKEN);

            return builder.toString();
        }

        private List<Photo> parseData(final String data) {

            if (data != null) {
                try {
                    JSONObject json = new JSONObject(data);
                    json = json.getJSONObject("photos");
                    JSONArray jsonArray = json.getJSONArray("items");
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Photo>>() {
                    }.getType();

                    return gson.fromJson(jsonArray.toString(), listType);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }

        /**
         * Initializes the ddbb with the data
         */
        private void initDB(final List<Photo> photoList) {

            PhotoDataSource photoDataSource = new PhotoDataSource(mContext);
            photoDataSource.open();
            for (Photo photo : photoList) {
                photoDataSource.insert(photo);
            }
            photoDataSource.close();
        }
    }

    public interface GetAllPhotosListener {

        public void getAllPhotosCallback(List<Photo> photoList);
    }
}
