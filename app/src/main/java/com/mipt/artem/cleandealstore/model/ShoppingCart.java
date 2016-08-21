package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public interface ShoppingCart {
    ItemInCart addItem(Item item, int count);
    ItemInCart increaseCount(ItemInCart id);
    ItemInCart decreaseCount(ItemInCart id);
    void deleteItem(ItemInCart id);
    List<ItemInCart> getAll();
}
