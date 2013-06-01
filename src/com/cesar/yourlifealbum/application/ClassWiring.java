package com.cesar.yourlifealbum.application;

import com.cesar.yourlifealbum.components.network.impl.ConnectionManagerImpl;
import com.cesar.yourlifealbum.components.tasks.EyeemTasks;
import com.cesar.yourlifealbum.managers.SharedPreferencesManager;

public class ClassWiring {

    private static ConnectionManagerImpl mConnectionManager;
    private static EyeemTasks mEyeemTasksManager;
    private static SharedPreferencesManager mSharedPreferencesManager;

    public static ConnectionManagerImpl getConnectionManager() {
        if (mConnectionManager == null) {
            mConnectionManager = new ConnectionManagerImpl();
        }
        return mConnectionManager;
    }

    public static EyeemTasks getEyeemTasksManager() {
        if (mEyeemTasksManager == null) {
            mEyeemTasksManager = new EyeemTasks();
        }
        return mEyeemTasksManager;
    }

    public static SharedPreferencesManager getSharedPreferencesManager() {
        if (mSharedPreferencesManager == null) {
            mSharedPreferencesManager = new SharedPreferencesManager();
        }
        return mSharedPreferencesManager;
    }
}
