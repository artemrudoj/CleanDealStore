package com.mipt.artem.cleandealstore.shoppingcart;

import android.os.Bundle;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.base.SubscriptionBasePresenter;
import com.mipt.artem.cleandealstore.model.ItemInCart;

/**
 * Created by artem on 21.08.16.
 */
public class ShoppingCartPresenter extends BaseGoodsPresenter {
    ShoppingCartView mView;

    public ShoppingCartPresenter(ShoppingCartView mShoppingCartView) {
        mView = mShoppingCartView;
    }

    public void clickItem(ItemInCart item) {

    }

    public void decreaseCount(ItemInCart item) {
        mShoppingCart.decreaseCount(item);
    }

    public void increaseCount(ItemInCart item) {
        mShoppingCart.increaseCount(item);
    }

    public void delete(ItemInCart item) {
        mShoppingCart.deleteItem(item);
    }

    public void onCreateView(Bundle savedInstanceState) {
        mView.showData(mShoppingCart.getAll());
    }
}
