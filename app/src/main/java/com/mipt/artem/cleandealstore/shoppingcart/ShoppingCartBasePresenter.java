package com.mipt.artem.cleandealstore.shoppingcart;

import android.os.Bundle;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.model.ItemInCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;

/**
 * Created by artem on 21.08.16.
 */
public abstract class ShoppingCartBasePresenter extends BaseGoodsPresenter {
    ShoppingCartView mView;

    public ShoppingCartBasePresenter(ShoppingCartView mShoppingCartView) {
        mView = mShoppingCartView;
    }

    public void clickItem(ItemInCart item) {

    }

    public void decreaseCount(ItemInCart item) {
        Subscription subscription = mShoppingCart.decreaseCount(item)
                .subscribe(new Observer<Map<Integer, ItemInCart>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(Map<Integer, ItemInCart> items) {
                updateShoppingCart(items);
            }
        });
        addSubscription(subscription);
    }

    public void increaseCount(ItemInCart item) {
        Subscription subscription = mShoppingCart.increaseCount(item)
                .subscribe(new Observer<Map<Integer, ItemInCart>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Map<Integer, ItemInCart> items) {
                updateShoppingCart(items);
            }
        });
        addSubscription(subscription);
    }

    public void delete(ItemInCart item) {
        Subscription subscription = mShoppingCart.deleteItem(item).subscribe(new Observer<Map<Integer, ItemInCart>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Map<Integer, ItemInCart> items) {
                updateShoppingCart(items);
            }
        });
        addSubscription(subscription);
    }

    public void onCreateView(Bundle savedInstanceState) {
        loadData();
    }

    private void loadData() {
        Subscription subscription = mShoppingCart.getAll().subscribe(new Observer<Map<Integer, ItemInCart>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(Map<Integer, ItemInCart> items) {
                mView.stopLoading();
                updateShoppingCart(items);
            }
        });
        addSubscription(subscription);
        mView.showLoading();
    }

    public void itemSwiped(ItemInCart item) {
        Subscription subscription = mShoppingCart.changeBasket(item, !isItShoppingCart())
                .subscribe(new Observer<Map<Integer, ItemInCart>>() {
            @Override
            public void onCompleted() {
                mView.handleSuccessfulMoving();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Map<Integer, ItemInCart> items) {
                updateShoppingCart(items);
            }
        });
        addSubscription(subscription);
    }

    private void updateShoppingCart(Map<Integer, ItemInCart> items) {
        List<ItemInCart> listOfItems = new ArrayList<ItemInCart>(items.values());
        mView.showData(listOfItems);
    }

    protected abstract boolean isItShoppingCart();
}
