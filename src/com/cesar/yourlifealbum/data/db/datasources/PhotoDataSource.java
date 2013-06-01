package com.cesar.yourlifealbum.data.db.datasources;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;

import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.data.db.models.DataObject;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.utils.Log;

/**
 * 
 * @author Cesar Valiente Gordo
 * @mail cesar.valiente@gmail.com
 * 
 *       This class is used to manage the DB using the {@link Photo} object as
 *       its data type
 */
public class PhotoDataSource extends DataSource {

    private final String CLASS_NAME = getClass().getName();

    private String[] allColumns = { AppConstants.Data.COLUMN_ID,
            AppConstants.Data.COLUMN_THUMB_URL,
            AppConstants.Data.COLUMN_PHOTO_URL,
            AppConstants.Data.COLUMN_WEB_URL, AppConstants.Data.COLUMN_WIDTH,
            AppConstants.Data.COLUMN_HEIGHT, AppConstants.Data.COLUMN_UPDATED };

    /**
     * Constructor
     * 
     * @param context
     */
    public PhotoDataSource(final Context context) {
        super(context);
    }

    @Override
    public long insert(final DataObject dataObject) {

        if (dataObject != null && dataObject instanceof Photo) {

            Photo photo = (Photo) dataObject;
            return insert(photo.getId(), photo.getThumbUrl(),
                    photo.getPhotoUrl(), photo.getWebUrl(), photo.getWidth(),
                    photo.getHeight(), photo.getUpdated());
        } else {
            return -1;
        }
    }

    /**
     * 
     * @param id
     * @param thumbUrl
     * @param photoUrl
     * @param webUrl
     * @param width
     * @param height
     * @param updated
     * @return
     */
    public long insert(final String id, final String thumbUrl,
            final String photoUrl, final String webUrl, final int width,
            final int height, final String updated) {

        // Creates a contentValues to store the data
        ContentValues values = createCompleteContentValues(id, thumbUrl,
                photoUrl, webUrl, width, height, updated);

        try {
            // Inserts the values into the table
            return db.insertOrThrow(AppConstants.Data.TABLE_PHOTOS, null,
                    values);

        } catch (SQLiteConstraintException sqle) {
            /*
             * Controlled error regarding when we try to insert a duplicate
             * element (it happens when we resync the data and we still have
             * some data in the list.
             */
            Log.d(CLASS_NAME, "Controlled duplicate insert error");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 
     * @param id
     * @param thumbUrl
     * @param photoUrl
     * @param webUrl
     * @param width
     * @param height
     * @param updated
     * @return
     */
    private ContentValues createCompleteContentValues(final String id,
            final String thumbUrl, final String photoUrl, final String webUrl,
            final int width, final int height, final String updated) {

        // Creates a contentValues to store the data
        ContentValues values = new ContentValues();
        values.put(AppConstants.Data.COLUMN_ID, id);
        values.put(AppConstants.Data.COLUMN_THUMB_URL, thumbUrl);
        values.put(AppConstants.Data.COLUMN_PHOTO_URL, photoUrl);
        values.put(AppConstants.Data.COLUMN_WEB_URL, webUrl);
        values.put(AppConstants.Data.COLUMN_WIDTH, width);
        values.put(AppConstants.Data.COLUMN_HEIGHT, height);
        values.put(AppConstants.Data.COLUMN_UPDATED, updated);

        return values;
    }

    /**
     * Returns the number of items stored in the table
     * 
     * @return
     */
    public long getNumdItems() {

        try {
            SQLiteStatement statement = db
                    .compileStatement("SELECT count(*) FROM "
                            + AppConstants.Data.TABLE_PHOTOS);
            return statement.simpleQueryForLong();
        } catch (SQLiteDoneException e) {
            return 0;
        }
    }

    @Override
    public void delete(final String id) {

        db.delete(AppConstants.Data.TABLE_PHOTOS, AppConstants.Data.COLUMN_ID
                + "=\"" + id + "\";", null);
    }

    @Override
    public long update(final String id, final DataObject dataObject) {

        if (dataObject != null && dataObject instanceof Photo) {
            Photo photo = (Photo) dataObject;

            return update(id, photo.getId(), photo.getThumbUrl(),
                    photo.getPhotoUrl(), photo.getWebUrl(), photo.getWidth(),
                    photo.getHeight(), photo.getUpdated());
        } else {
            return -1;
        }
    }

    public long update(final String newId, final String oldId,
            final String thumbUrl, final String photoUrl, final String webUrl,
            final int width, final int height, final String updated) {

        ContentValues values = createCompleteContentValues(oldId, thumbUrl,
                photoUrl, webUrl, width, height, updated);

        return db.update(AppConstants.Data.TABLE_PHOTOS, values,
                AppConstants.Data.COLUMN_ID + "=\"" + newId + "\";", null);
    }

    @Override
    public List<DataObject> getAll() {

        List<DataObject> list = new ArrayList<DataObject>();
        Cursor cursor = db.query(AppConstants.Data.TABLE_PHOTOS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Photo photo = (Photo) cursorToDataObject(cursor);
            list.add(photo);
            cursor.moveToNext();
        }
        return list;
    }

    @Override
    protected DataObject cursorToDataObject(final Cursor cursor) {

        if (cursor != null) {
            Photo data = new Photo(cursor.getString(0));
            data.setThumbUrl(cursor.getString(1));
            data.setPhotoUrl(cursor.getString(2));
            data.setWebUrl(cursor.getString(3));
            data.setWidth(cursor.getInt(4));
            data.setHeight(cursor.getInt(5));
            data.setUpdated(cursor.getString(6));

            return data;
        } else {
            return null;
        }
    }
}
