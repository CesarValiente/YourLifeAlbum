package com.cesar.yourlifealbum.application;

import android.app.Application;

/**
 * 
 * @author cesar.valiente@gmail.com
 * 
 *         This class is used as Application class which is launched when the
 *         app starts. This class has all global stuff we need to use along the
 *         app as static way.
 * 
 */
public class YLAApplication extends Application {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private static YLAApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static YLAApplication getInstance() {
        return sInstance;
    }
}
