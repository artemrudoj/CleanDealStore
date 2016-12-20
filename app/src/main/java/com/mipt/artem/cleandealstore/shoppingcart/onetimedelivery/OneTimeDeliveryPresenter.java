package com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery;

import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBasePresenter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartView;

/**
 * Created by artem on 09.12.16.
 */

public class OneTimeDeliveryPresenter extends ShoppingCartBasePresenter {
    public OneTimeDeliveryPresenter(ShoppingCartView mShoppingCartView) {
        super(mShoppingCartView);
    }

    @Override
    protected boolean isItShoppingCart() {
        return false;
    }
}
