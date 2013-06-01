package com.cesar.yourlifealbum.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.ui.fragments.YLALoginFragment;

public class YLAMainFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.yla_main_layout);

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.add(R.id.yla_main_activity_layout, new YLALoginFragment());
        transaction.commit();
    }
}
