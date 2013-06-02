package com.cesar.yourlifealbum.components.adapters;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.ui.fragments.PhotoFragment;

/**
 * @author cesar.valiente@gmail.com
 * 
 *         This is the adapter incharged to manage the different
 *         {@link Fragment} we will show in the {@link XBWelcomeFragment} which
 *         uses a {@link ViewPager}
 * 
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFragments;
    private List<Photo> mPhotoList;

    public ViewPagerAdapter(final FragmentManager fm,
            final List<Photo> photoList) {
        super(fm);

        mPhotoList = photoList;
        init();
    }

    public void init() {
        mListFragments = new ArrayList<Fragment>();

        if (mPhotoList != null) {
            for (Photo item : mPhotoList) {
                mListFragments.add(new PhotoFragment(item.getPhotoUrl()));
            }
        }
    }

    @Override
    public Fragment getItem(final int position) {

        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mListFragments != null) {
            return mListFragments.size();
        } else {
            return 0;
        }
    }
}
