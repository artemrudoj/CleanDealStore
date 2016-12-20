package com.mipt.artem.cleandealstore.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.mipt.artem.cleandealstore.model.ItemInCart;

import java.util.List;

/**
 * Created by artem on 13.12.16.
 */

public class ItemsInCartDiffCallback extends DiffUtil.Callback {
    private List<ItemInCart> mOldList;
    private List<ItemInCart> mNewList;

    public ItemsInCartDiffCallback(List<ItemInCart> oldList, List<ItemInCart> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList != null ? mOldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).getId() == mOldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).getCount() == mOldList.get(newItemPosition).getCount();
    }

}