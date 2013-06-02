package com.cesar.yourlifealbum.ui.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.components.adapters.ViewPagerAdapter;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.utils.Log;
import com.viewpagerindicator.CirclePageIndicator;

@SuppressLint("ValidFragment")
public class ViewPhotosFragment extends Fragment {

    private final String CLASS_NAME = ViewPhotosFragment.class.getSimpleName();

    private ViewPager mViewPager;
    private CirclePageIndicator mCircleIndicator;
    private ViewPagerAdapter mPagerAdapter;

    private List<Photo> mPhotoList;

    public ViewPhotosFragment(final List<Photo> photoList) {
        mPhotoList = photoList;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        Log.d(CLASS_NAME, "fragment!!!!");
        return inflater.inflate(R.layout.view_photos_layout, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mCircleIndicator = (CirclePageIndicator) view
                .findViewById(R.id.circle_indicator);

        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),
                mPhotoList);
        mViewPager.setAdapter(mPagerAdapter);
        mCircleIndicator.setViewPager(mViewPager);

    }

}
