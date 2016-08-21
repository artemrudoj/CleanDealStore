package com.mipt.artem.cleandealstore.base;



import com.mipt.artem.cleandealstore.model.GoodsModel;
import com.mipt.artem.cleandealstore.model.ShoppingCart;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseGoodsPresenter implements Presenter {

    @Inject
    protected GoodsModel model;

    @Inject
    protected ShoppingCart mShoppingCart;



    @Inject
    protected CompositeSubscription compositeSubscription;

    public BaseGoodsPresenter() {
        CleanDealStoreApplication.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

}
