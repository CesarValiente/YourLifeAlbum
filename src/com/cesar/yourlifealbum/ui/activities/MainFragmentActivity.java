package com.cesar.yourlifealbum.ui.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.ui.fragments.CalendarFragment;

public class MainFragmentActivity extends Activity {

    @Override
    protected void onCreate(final Bundle arg0) {
        super.onCreate(arg0);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.yla_main_layout);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.add(R.id.yla_main_activity_layout, new CalendarFragment());
        transaction.commit();
    }
}
