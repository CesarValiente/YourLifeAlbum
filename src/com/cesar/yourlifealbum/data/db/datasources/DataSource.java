package com.cesar.yourlifealbum.data.db.datasources;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cesar.yourlifealbum.data.DBHelper;
import com.cesar.yourlifealbum.data.db.models.DataObject;

/**
 * 
 * @author Cesar Valiente Gordo
 * @mail cesar.valiente@gmail.com
 * 
 *       This class is the default class to use the general functions of the
 *       data sources
 */
public abstract class DataSource {

    // Database fields
    protected SQLiteDatabase db;
    protected DBHelper dbHelper;

    /**
     * Constructor
     * 
     * @param context
     */
    public DataSource(final Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Opens the database
     * 
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Closes the database
     * 
     * @throws SQLException
     */
    public void close() throws SQLException {
        db.close();
    }

    /**
     * Insert a DataObject in the database
     * 
     * @param group
     * @return
     */
    public abstract long insert(DataObject object);

    /**
     * Deletes the object
     * 
     * @param id
     */
    public abstract void delete(String id);

    /**
     * Updates the specified object
     * 
     * @param id
     * @param dataObject
     */
    public abstract long update(String id, DataObject dataObject);

    /**
     * Get all objects from the table
     * 
     * @return
     */
    public abstract List<DataObject> getAll();

    /**
     * Creates a DataObject from a cursor.
     * 
     * @param cursor
     * @return
     */
    protected abstract DataObject cursorToDataObject(Cursor cursor);

}
