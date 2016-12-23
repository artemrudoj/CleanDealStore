package com.mipt.artem.cleandealstore.shoppingcart;

import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.model.ShoppingCartWithNetworkImpl;

import javax.inject.Inject;

/**
 * Created by artem on 22.12.16.
 */

public class ShoppingCartContainerPresenter implements Presenter {

    @Inject
    protected ShoppingCartWithNetworkImpl mShoppingCart;


    void calculateSumCost() {
//        mShoppingCart.getAll()
    }

    @Override
    public void onStop() {

    }
}
