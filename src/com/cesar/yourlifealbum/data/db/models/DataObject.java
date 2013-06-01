package com.cesar.yourlifealbum.data.db.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Cesar Valiente Gordo
 * @mail cesar.valiente@gmail.com
 * 
 *       This class is used as a parent class of the all different data objects
 *       we can have in the app.
 * 
 */
public class DataObject implements Parcelable {

    private final String CLASS_NAME = getClass().getSimpleName();

    private static final String API_ID = "id";

    @SerializedName(API_ID)
    protected String id;

    /**
     * Empty constructor
     */
    public DataObject() {
    }

    /**
     * @param id
     */
    public DataObject(final String id) {
        this.id = id;
    }

    // ----------- Getters & Setters -----------//
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    // ---------------- Parcelable packing/unpacking --------------//

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(id);
    }

    /**
     * Creates a country object from a Parcelable object
     */
    public static final Parcelable.Creator<DataObject> CREATOR = new Parcelable.Creator<DataObject>() {

        @Override
        public DataObject createFromParcel(final Parcel source) {

            DataObject dataObject = new DataObject();
            dataObject.setId(source.readString());

            return dataObject;
        }

        @Override
        public DataObject[] newArray(final int size) {
            return new DataObject[size];
        }
    };

}
