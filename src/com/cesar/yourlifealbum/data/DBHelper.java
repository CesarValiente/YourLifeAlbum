package com.cesar.yourlifealbum.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.utils.Log;

/**
 * 
 * @author Cesar Valiente Gordo
 * @mail cesar.valiente@gmail.com
 * 
 *       This class has the main general functions to manage the ddbb
 * 
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String CLASS_NAME = DBHelper.class.getName();

    public DBHelper(final Context context) {
        super(context, AppConstants.Data.DB_NAME, null,
                AppConstants.Data.DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {

        Log.d(CLASS_NAME, "creating database......!!!!!");

        db.execSQL(AppConstants.Data.CREATE_TABLE_PHOTOS);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
            final int newVersion) {
        Log.i(CLASS_NAME, "Updating database from the version " + oldVersion
                + " to the version " + newVersion);

        Log.d(CLASS_NAME, "deleting database .........!!!!!");
        db.execSQL(AppConstants.Data.DROP_TABLE_PHOTOS);

        onCreate(db);
    }
}
