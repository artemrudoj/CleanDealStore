package com.mipt.artem.cleandealstore.model;

import android.os.Parcel;

import com.mipt.artem.cleandealstore.rest.responcedata.Item;

/**
 * Created by artem on 21.08.16.
 */
public class ItemInCart extends Item {
    protected ItemInCart(Parcel in) {
        super(in);
    }


    public ItemInCart(Item item, int count) {
        mCount = count;
        id = item.getId();
        imageUrl = item.getImageUrl();
        nameFull = item.getNameFull();
        cost = item.getCost();
        dateAdded = item.getDateAdded();
        about = item.getAbout();
        extraInfo = item.getExtraInfo();
        subcategory = item.getSubcategory();
    }

    public int getmCount() {
        return mCount;
    }

    public int getmLocalId() {
        return mLocalId;
    }

    public void setmLocalId(int mLocalId) {
        this.mLocalId = mLocalId;
    }

    private int mCount;
    private int mLocalId;



    public int getCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}
