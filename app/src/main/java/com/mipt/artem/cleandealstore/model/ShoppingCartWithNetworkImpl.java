package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.rest.ApiInterface;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;


import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by artem on 08.12.16.
 */

public class ShoppingCartWithNetworkImpl  {
    private final Observable.Transformer schedulersTransformer;
    @Inject
    protected ApiInterface mApiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ShoppingCartWithNetworkImpl(ApiInterface apiInterface, Scheduler uiThread, Scheduler ioThread) {
        this.mApiInterface = apiInterface;
        this.schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread).observeOn(uiThread);
        this.uiThread = uiThread;
        this.ioThread = ioThread;
    }


    public Observable<Map<Integer, ItemInCart>> addItem(Item item, int count) {
        return mApiInterface
                .editItemInShoppingCart(item.getId(), true, 1, null)
                .compose(this.applySchedulers());
    }


    public Observable<Map<Integer, ItemInCart>> increaseCount(ItemInCart item) {
        return mApiInterface
                .editItemInShoppingCart(item.getId(), null, item.getCount() + 1, null)
                .compose(this.applySchedulers());
    }

    public Observable<Map<Integer, ItemInCart>> changeBasket(ItemInCart item, boolean isMoveToShoppingCart) {
        return mApiInterface
                .editItemInShoppingCart(item.getId(), isMoveToShoppingCart, null, null)
                .compose(this.applySchedulers());
    }


    public Observable<Map<Integer, ItemInCart>> decreaseCount(ItemInCart item) {
        int itemsCount = item.getCount() - 1;
//        if (itemsCount <= 0) throw new IllegalArgumentException("cannot decrease count of items");
        return mApiInterface
                .editItemInShoppingCart(item.getId(), null, itemsCount, null)
                .compose(this.applySchedulers());
    }

    public Observable<Map<Integer, ItemInCart>> deleteItem(ItemInCart item) {
        return mApiInterface
                .deleteItemFromShoppingCart(item.getId())
                .compose(this.applySchedulers());
    }


    public Observable<Map<Integer, ItemInCart>> getAll() {
        return mApiInterface
                .getShoppingCart()
                .compose(this.applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
