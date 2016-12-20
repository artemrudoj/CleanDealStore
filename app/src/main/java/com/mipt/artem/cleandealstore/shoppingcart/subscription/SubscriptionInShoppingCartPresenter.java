package com.mipt.artem.cleandealstore.shoppingcart.subscription;

import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBasePresenter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartView;

/**
 * Created by artem on 09.12.16.
 */

public class SubscriptionInShoppingCartPresenter extends ShoppingCartBasePresenter {
    public SubscriptionInShoppingCartPresenter(ShoppingCartView mShoppingCartView) {
        super(mShoppingCartView);
    }

    @Override
    protected boolean isItShoppingCart() {
        return true;
    }


}
