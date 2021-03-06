package com.mipt.artem.cleandealstore.di;


import com.mipt.artem.cleandealstore.model.AuthenticationService;
import com.mipt.artem.cleandealstore.model.ShoppingCartWithNetworkImpl;
import com.mipt.artem.cleandealstore.model.User;
import com.mipt.artem.cleandealstore.model.UserController;
import com.mipt.artem.cleandealstore.rest.ApiInterface;
import com.mipt.artem.cleandealstore.rest.ApiModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface(Const.BASE_URL);
    }

    @Provides
    @Singleton
    ShoppingCartWithNetworkImpl provideShoppingCartManager(ApiInterface apiInterface,
                                                           @Named(Const.UI_THREAD)Scheduler uischeduler,
                                                           @Named(Const.IO_THREAD)Scheduler ioscheduler) {
        return new ShoppingCartWithNetworkImpl(apiInterface, uischeduler, ioscheduler);
    }

    @Provides
    @Singleton
    AuthenticationService provideAuthService(ApiInterface apiInterface,
                                             @Named(Const.UI_THREAD)Scheduler uischeduler,
                                             @Named(Const.IO_THREAD)Scheduler ioscheduler) {
        return new AuthenticationService(apiInterface, uischeduler, ioscheduler);
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    User provideUser() {
        return new User();
    }

    @Provides
    @Singleton
    UserController provideUserController(ApiInterface apiInterface,
                                         @Named(Const.UI_THREAD)Scheduler uischeduler,
                                         @Named(Const.IO_THREAD)Scheduler ioscheduler,
                                         User user) {
        return new UserController(apiInterface, uischeduler, ioscheduler, user);
    }
}
