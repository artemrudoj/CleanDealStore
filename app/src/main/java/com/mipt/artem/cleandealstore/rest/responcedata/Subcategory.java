package com.mipt.artem.cleandealstore.rest.responcedata;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 15.08.16.
 */
public class Subcategory implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("category")
    private int category;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The category
     */
    public int getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(int category) {
        this.category = category;
    }


    protected Subcategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Subcategory> CREATOR = new Parcelable.Creator<Subcategory>() {
        @Override
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size) {
            return new Subcategory[size];
        }
    };
}