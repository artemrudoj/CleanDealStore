package com.mipt.artem.cleandealstore.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public class ItemInCart extends Item {

    @SerializedName("count")
    private int mCount;

    @SerializedName("in_rack")
    private boolean isInShoppingCart;

    @SerializedName("people_count")
    private int mPeopleCount;


    protected ItemInCart(Parcel in) {
        super(in);
        mCount = in.readInt();
        isInShoppingCart = in.readByte() != 0;
        mPeopleCount = in.readInt();
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

    public static final Creator<ItemInCart> CREATOR = new Creator<ItemInCart>() {
        @Override
        public ItemInCart createFromParcel(Parcel in) {
            return new ItemInCart(in);
        }

        @Override
        public ItemInCart[] newArray(int size) {
            return new ItemInCart[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mCount);
        dest.writeByte((byte) (isInShoppingCart ? 1 : 0));
        dest.writeInt(mPeopleCount);
    }

    public int getmCount() {
        return mCount;
    }

    public boolean isInShoppingCart() {
        return isInShoppingCart;
    }

    public void setInShoppingCart(boolean inShoppingCart) {
        isInShoppingCart = inShoppingCart;
    }

    public int getPeopleCount() {
        return mPeopleCount;
    }

    public void setPeopleCount(int mPeopleCount) {
        this.mPeopleCount = mPeopleCount;
    }

    public int getCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public static List<ItemInCart> filterByBasket(List<ItemInCart> items, boolean isInShoppingCart) {
        List<ItemInCart> itemInCarts = new ArrayList<>();
        for (ItemInCart item : items) {
            if (item.isInShoppingCart() == isInShoppingCart) {
                itemInCarts.add(item);
            }
        }
        return itemInCarts;
    }
}
