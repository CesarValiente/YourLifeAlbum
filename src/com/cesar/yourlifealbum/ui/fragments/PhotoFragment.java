package com.cesar.yourlifealbum.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.squareup.picasso.Picasso;

@SuppressLint("ValidFragment")
public class PhotoFragment extends Fragment {

    private String mPhotoUrl;

    public PhotoFragment(final String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());
        Picasso.with(getActivity()).load(mPhotoUrl).into(imageView);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        layout.setGravity(Gravity.CENTER);
        layout.addView(imageView);

        return layout;
    }

}
