package com.mipt.artem.cleandealstore.shoppingcart;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.model.ItemInCart;

import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public interface ShoppingCartView extends RecycledBaseView<ItemInCart> {
    void handleSuccessfulMoving();
    void updatePrice(List<ItemInCart> items);
}
