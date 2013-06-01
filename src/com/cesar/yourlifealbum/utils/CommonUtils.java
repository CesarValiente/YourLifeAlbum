package com.cesar.yourlifealbum.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author cesar.valiente@gmail.com
 * 
 *         This class has general functions to perform different things in the
 *         app
 */
public class CommonUtils {

    /**
     * Checks if the device has internet connection
     * 
     * @return
     */
    public static boolean hasInternetConnection(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if ((networkInfo != null) && networkInfo.isAvailable()
                && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the SD card is mounted
     * 
     * @return
     */
    public static boolean hasSDCardMounted() {
        return (android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED));
    }

}
