package com.cesar.yourlifealbum.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.ui.fragments.CalendarFragment;

public class MainFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle arg0) {
        super.onCreate(arg0);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.main_layout);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.add(R.id.main_layout, new CalendarFragment());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_settings:

                return true;
            case R.id.menu_refresh:

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
