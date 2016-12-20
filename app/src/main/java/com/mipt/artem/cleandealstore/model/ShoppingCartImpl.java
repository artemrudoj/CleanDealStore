package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public class ShoppingCartImpl  {
    List<ItemInCart> mItems = new ArrayList<>();

    public ItemInCart addItem(Item item, int count) {
        for (ItemInCart itemInCart : mItems) {
            if (itemInCart.getId() == item.getId()) {
                itemInCart.setmCount(itemInCart.getCount() + count);
                return itemInCart;
            }
        }
        ItemInCart newItem = new ItemInCart(item, count);
        mItems.add(newItem);
        return newItem;
    }


    public ItemInCart increaseCount(ItemInCart item) {
        for (ItemInCart itemInCart : mItems) {
            if (itemInCart.getId() == item.getId()) {
                itemInCart.setmCount(itemInCart.getCount() + 1);
                return itemInCart;
            }
        }
        throw new IllegalStateException("can not found item");
    }


    public ItemInCart decreaseCount(ItemInCart item) {
        for (ItemInCart itemInCart : mItems) {
            if (itemInCart.getId() == item.getId()) {
                int itemCount = itemInCart.getCount() - 1;
                if (itemCount >= 1 ) {
                    itemInCart.setmCount(itemCount);
                }
                return itemInCart;
            }
        }
        throw new IllegalStateException("can not found item");
    }


    public void deleteItem(ItemInCart item) {
        if (!mItems.remove(item)) {
            throw new IllegalStateException("can not found item");
        }
    }


    public List<ItemInCart> getAll() {
        return mItems;
    }
}
