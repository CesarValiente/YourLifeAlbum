package com.cesar.yourlifealbum.data.db.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photo extends DataObject implements Parcelable {

    private static final String CLASS_NAME = Photo.class.getSimpleName();

    private static final String API_THUMB_URL = "thumbUrl";
    private static final String API_PHOTO_URL = "photoUrl";
    private static final String API_WEB_URL = "webUrl";
    private static final String API_WIDTH = "width";
    private static final String API_HEIGHT = "height";
    private static final String API_UPDATED = "updated";

    @SerializedName(API_THUMB_URL)
    private String thumbUrl;

    @SerializedName(API_PHOTO_URL)
    private String photoUrl;

    @SerializedName(API_WEB_URL)
    private String webUrl;

    @SerializedName(API_WIDTH)
    private int width;

    @SerializedName(API_HEIGHT)
    private int height;

    @SerializedName(API_UPDATED)
    private String updated;

    private int year;
    private int month;
    private int day;

    public Photo() {
        super();
    }

    public Photo(final String id) {
        super(id);
    }

    // ----------- Getters & Setters ------------//

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(final String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(final String webUrl) {
        this.webUrl = webUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(final String updated) {
        this.updated = updated;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(final int day) {
        this.day = day;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(id).append("\n").append(thumbUrl).append("\n")
                .append(photoUrl).append("\n").append(webUrl).append("\n")
                .append(String.valueOf(width)).append("\n")
                .append(String.valueOf(height)).append("\n").append(updated);
        return builder.toString();
    }

    // ---------------- Parcelable packing/unpacking --------------//

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(thumbUrl);
        dest.writeString(photoUrl);
        dest.writeString(webUrl);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(updated);
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
    }

    /**
     * Creates a country object from a Parcelable object
     */
    public static final Parcelable.Creator<DataObject> CREATOR = new Parcelable.Creator<DataObject>() {

        @Override
        public DataObject createFromParcel(final Parcel source) {

            Photo photo = new Photo(source.readString());
            photo.setThumbUrl(source.readString());
            photo.setPhotoUrl(source.readString());
            photo.setWebUrl(source.readString());
            photo.setWidth(source.readInt());
            photo.setHeight(source.readInt());
            photo.setUpdated(source.readString());
            photo.setYear(source.readInt());
            photo.setMonth(source.readInt());
            photo.setDay(source.readInt());

            return photo;
        }

        @Override
        public DataObject[] newArray(final int size) {
            return new DataObject[size];
        }
    };

}
