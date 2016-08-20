package com.mipt.artem.cleandealstore.rest.responcedata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by artem on 20.08.16.
 */
public class ItemsHolder {
    @SerializedName("results")
    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
