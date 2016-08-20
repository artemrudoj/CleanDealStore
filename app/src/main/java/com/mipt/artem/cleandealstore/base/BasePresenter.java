package com.mipt.artem.cleandealstore.base;



import com.mipt.artem.cleandealstore.model.GoodsModel;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected GoodsModel model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
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
