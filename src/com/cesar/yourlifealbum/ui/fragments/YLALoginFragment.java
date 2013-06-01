package com.cesar.yourlifealbum.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesar.yourlifealbum.R;

public class YLALoginFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.yla_login_layout, container,
                false);

        return view;
    }
}
