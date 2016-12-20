package com.mipt.artem.cleandealstore.rest.responcedata;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.mipt.artem.cleandealstore.goods.category.info.CategoryWithDetailInfoHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 15.08.16.
 */
public class Category implements Parcelable {
    public static final Category ROOT = null;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("contains_categories")
    private int containsCategoriesCount;
    @SerializedName("contains_items")
    private int containsItemsCount;
    @SerializedName("parent_category")
    private int parentCategoryId;

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        containsCategoriesCount = in.readInt();
        containsItemsCount = in.readInt();
        parentCategoryId = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContainsCategoriesCount() {
        return containsCategoriesCount;
    }

    public void setContainsCategoriesCount(int containsCategoriesCount) {
        this.containsCategoriesCount = containsCategoriesCount;
    }

    public int getContainsItemsCount() {
        return containsItemsCount;
    }

    public void setContainsItemsCount(int containsItemsCount) {
        this.containsItemsCount = containsItemsCount;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(containsCategoriesCount);
        dest.writeInt(containsItemsCount);
        dest.writeInt(parentCategoryId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public static List<CategoryWithDetailInfoHolder> convertListToHolder(List<Category> data) {
        if (data == null) {
            return null;
        };
        List<CategoryWithDetailInfoHolder> list = new ArrayList<>();
        for (Category category : data) {
            list.add(new CategoryWithDetailInfoHolder(category));
        }
        return list;
    }
}