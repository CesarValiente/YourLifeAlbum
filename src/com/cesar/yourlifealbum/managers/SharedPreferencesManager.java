package com.cesar.yourlifealbum.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.application.YLAApplication;

/**
 * 
 * @author cesar.valiente@gmail.com
 * 
 *         This class is used to manage all stuff regarding
 *         {@link SharedPreferences}
 */
public class SharedPreferencesManager {

    private SharedPreferences getSharedPreferences() {

        return YLAApplication.getInstance().getSharedPreferences(
                AppConstants.Preferences.PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Checks if it's the first use of the application
     * 
     * @return
     */
    public boolean isFirstTimeLaunched() {

        return getSharedPreferences().getBoolean(
                AppConstants.Preferences.PREFS_FIRST_USE, true);
    }

    /**
     * Sets the application as already launched.
     */
    public void markAsFirstTimeLaunched() {

        Editor editor = getSharedPreferences().edit();
        editor.putBoolean(AppConstants.Preferences.PREFS_FIRST_USE, false);
        editor.commit();
    }

}
