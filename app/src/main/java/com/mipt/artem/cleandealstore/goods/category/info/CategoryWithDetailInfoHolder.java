package com.mipt.artem.cleandealstore.goods.category.info;

import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.List;

/**
 * Created by artem on 10.12.16.
 */

public class CategoryWithDetailInfoHolder {
    public static final int EMPTY = -1;
    public static final int CATEGORIES_HOLDER = 0;
    public static final int ITEMS_HOLDER = 1;
    private boolean isDetailShown;
    private Category mCategory;
    private List<Category> mChildDirectories;
    private List<Item> mItems;


    public Category getCategory() {
        return mCategory;
    }

    public int getCurrentDataType() {
        if (mChildDirectories == null && mItems == null) {
            return EMPTY;
        } else if (mChildDirectories != null && mItems == null) {
            return CATEGORIES_HOLDER;
        } else if (mChildDirectories == null && mItems != null) {
            return ITEMS_HOLDER;
        } else {
            throw new IllegalStateException("childs and item cannot be not null together");
        }
    }

    public List<Item> getItems() {
        return mItems;
    }

    public List<Category> getChildDirectories() {
        return mChildDirectories;
    }

    public boolean isDetailShown() {
        return isDetailShown;
    }

    public void setChildDirectories(List<Category> mChildDirectories) {
        this.mChildDirectories = mChildDirectories;
    }

    public void setCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    public void setDetailShown(boolean detailShown) {
        isDetailShown = detailShown;
    }

    public void setItems(List<Item> mItems) {
        this.mItems = mItems;
    }

    public CategoryWithDetailInfoHolder(Category category) {
        this.mCategory = category;
    }
}
