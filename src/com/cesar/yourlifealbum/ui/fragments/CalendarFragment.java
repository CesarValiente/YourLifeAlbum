package com.cesar.yourlifealbum.ui.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.application.ClassWiring;
import com.cesar.yourlifealbum.components.tasks.EyeemTasks.GetAllPhotosListener;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.utils.Log;

public class CalendarFragment extends Fragment implements GetAllPhotosListener {

    private final String CLASS_NAME = CalendarFragment.class.getSimpleName();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClassWiring.getEyeemTasksManager().new GetAllPhotos(getActivity(), this)
                .execute();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.yla_login_layout, container, false);
    }

    @Override
    public void getAllPhotosCallback(final List<Photo> photoList) {

        if (photoList != null) {
            for (Photo item : photoList) {
                Log.d(CLASS_NAME, item.toString());
            }
        }

    }
}
