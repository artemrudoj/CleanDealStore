package com.mipt.artem.cleandealstore.shoppingcart;

import com.mipt.artem.cleandealstore.model.ItemInCart;

import java.util.List;

/**
 * Created by artem on 13.12.16.
 */
public interface ItemsUpdateListener {
    void onUpdateItems(List<ItemInCart> items);
}
