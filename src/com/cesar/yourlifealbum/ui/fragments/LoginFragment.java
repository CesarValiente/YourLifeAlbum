package com.cesar.yourlifealbum.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.cesar.yourlifealbum.R;

public class LoginFragment extends Fragment {

    private final String CLASS_NAME = LoginFragment.class.getSimpleName();

    // Widgets
    private Button mLoginBtn;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_layout, container, false);

        mLoginBtn = (Button) view.findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });
        return view;
    }

}
