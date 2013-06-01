package com.cesar.yourlifealbum.utils;

import com.cesar.yourlifealbum.application.AppConstants;

/**
 * 
 * @author cesar.valiente@gmail.com
 * 
 *         This class is used to manage all logs events
 * 
 */
public class Log {

    public static void v(final String className, final String msg) {
        if (AppConstants.Development.DEBUG) {
            android.util.Log.v(className, msg);
        }
    }

    public static void d(final String className, final String msg) {
        if (AppConstants.Development.DEBUG) {
            android.util.Log.d(className, msg);
        }
    }

    public static void i(final String className, final String msg) {
        if (AppConstants.Development.DEBUG) {
            android.util.Log.i(className, msg);
        }
    }

    public static void w(final String className, final String msg) {
        if (AppConstants.Development.DEBUG) {
            android.util.Log.w(className, msg);
        }
    }

    public static void e(final String className, final String msg) {
        if (AppConstants.Development.DEBUG) {
            android.util.Log.e(className, msg);
        }
    }

}
