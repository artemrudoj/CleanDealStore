package com.mipt.artem.cleandealstore.rest.responcedata;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;

/**
 * Created by artem on 15.08.16.
 */
public class Item implements Parcelable {

    public static final String EXTRA_ITEM = "Item.EXTRA_ITEM";
    @SerializedName("id")
    protected int id;
    @SerializedName("image_url")
    protected String imageUrl;
    @SerializedName("name_full")
    protected String nameFull;
    @SerializedName("cost")
    protected String cost;
    @SerializedName("date_added")
    protected String dateAdded;
    @SerializedName("about")
    protected String about;
    @SerializedName("extra_info")
    protected ExtraInfo extraInfo;
    @SerializedName("subcategory")
    protected int subcategory;

    public Item() {
    }

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
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The nameFull
     */
    public String getNameFull() {
        return nameFull;
    }

    /**
     *
     * @param nameFull
     * The name_full
     */
    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    /**
     *
     * @return
     * The cost
     */
    public String getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     * The cost
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     *
     * @return
     * The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     * The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return
     * The about
     */
    public String getAbout() {
        return about;
    }

    /**
     *
     * @param about
     * The about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     *
     * @return
     * The extraInfo
     */
    public ExtraInfo getExtraInfo() {
        return extraInfo;
    }

    /**
     *
     * @param extraInfo
     * The extra_info
     */
    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     *
     * @return
     * The subcategory
     */
    public int getSubcategory() {
        return subcategory;
    }

    /**
     *
     * @param subcategory
     * The subcategory
     */
    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        imageUrl = in.readString();
        nameFull = in.readString();
        cost = in.readString();
        dateAdded = in.readString();
        about = in.readString();
        extraInfo = (ExtraInfo) in.readValue(ExtraInfo.class.getClassLoader());
        subcategory = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imageUrl);
        dest.writeString(nameFull);
        dest.writeString(cost);
        dest.writeString(dateAdded);
        dest.writeString(about);
        dest.writeValue(extraInfo);
        dest.writeInt(subcategory);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public static Item getItemFromBundle(Bundle bundle) {
        Item item;
        if (bundle.containsKey(EXTRA_ITEM)) {
            item = bundle.getParcelable(EXTRA_ITEM);
        } else {
            throw new IllegalArgumentException("cannot find item");
        }
        return item;
    }
}