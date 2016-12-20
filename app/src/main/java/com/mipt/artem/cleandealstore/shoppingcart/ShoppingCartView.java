package com.mipt.artem.cleandealstore.shoppingcart;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.model.ItemInCart;

/**
 * Created by artem on 21.08.16.
 */
public interface ShoppingCartView extends RecycledBaseView<ItemInCart> {
    void handleSuccessfulMoving();
}
