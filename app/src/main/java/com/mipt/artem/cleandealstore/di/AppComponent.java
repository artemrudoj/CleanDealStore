package com.mipt.artem.cleandealstore.di;


import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.base.SubscriptionBasePresenter;
import com.mipt.artem.cleandealstore.di.user.UserComponent;
import com.mipt.artem.cleandealstore.di.user.UserModule;
import com.mipt.artem.cleandealstore.model.GoodsModel;
import com.mipt.artem.cleandealstore.user.changeinfo.ChangeUserInfoPresenter;
import com.mipt.artem.cleandealstore.user.registration.RegistrationNumberPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {
    UserComponent plus(UserModule userModule);

    void inject(BaseGoodsPresenter baseGoodsPresenter);

    void inject(GoodsModel model);

    void inject(SubscriptionBasePresenter subscriptionBasePresenter);

    void inject(RegistrationNumberPresenter registrationNumberPresenter);

    void inject(ChangeUserInfoPresenter changeUserInfoPresenter);
}
