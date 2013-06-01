package com.cesar.yourlifealbum.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.application.ClassWiring;
import com.cesar.yourlifealbum.components.tasks.EyeemTasks;
import com.cesar.yourlifealbum.utils.Log;

public class LoginFragment extends Fragment implements OnClickListener {

    private final String CLASS_NAME = LoginFragment.class.getSimpleName();

    // Widgets
    private Button mLoginBtn;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;

    private EyeemTasks mEyeemTasksManager;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEyeemTasksManager = ClassWiring.getEyeemTasksManager();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_layout, container, false);

        mUserNameEditText = (EditText) view.findViewById(R.id.login_username);
        mPasswordEditText = (EditText) view.findViewById(R.id.login_password);
        mLoginBtn = (Button) view.findViewById(R.id.login_btn);

        mLoginBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(final View v) {
        Log.d(CLASS_NAME, "click");

        String userName = mUserNameEditText.getText().toString();
        String passwd = mPasswordEditText.getText().toString();
    }

}
