package com.mipt.artem.cleandealstore.di;


import com.mipt.artem.cleandealstore.model.GoodsModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module

public class PresenterModule {

    //// TODO: 16.08.16 move to interface
    @Provides
    @Singleton
    GoodsModel provideDataRepository() {
        return new GoodsModel();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
