package com.mipt.artem.cleandealstore.di;


import com.mipt.artem.cleandealstore.model.GoodsModel;
import com.mipt.artem.cleandealstore.model.SubscriptionModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {


    @Provides
    @Singleton
    GoodsModel provideGoodsModel() {
        return new GoodsModel();
    }

    @Provides
    @Singleton
    SubscriptionModel provideSubscriptionModel() {
        return new SubscriptionModel();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
