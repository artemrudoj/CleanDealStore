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


    @SerializedName("count")
    private int mCount;

    @SerializedName("in_rack")
    private boolean isInShoppingCart;

    public boolean isInShoppingCart() {
        return isInShoppingCart;
    }

    public void setInShoppingCart(boolean inShoppingCart) {
        isInShoppingCart = inShoppingCart;
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
