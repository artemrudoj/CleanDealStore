package com.mipt.artem.cleandealstore.base;

import com.mipt.artem.cleandealstore.model.GoodsModel;
import com.mipt.artem.cleandealstore.model.SubscriptionModel;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by artem on 21.08.16.
 */
public class SubscriptionBasePresenter implements Presenter {

    @Inject
    protected SubscriptionModel model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public SubscriptionBasePresenter() {
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

