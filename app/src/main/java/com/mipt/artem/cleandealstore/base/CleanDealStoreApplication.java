package com.mipt.artem.cleandealstore.base;

import android.app.Application;
import android.content.Context;

import com.mipt.artem.cleandealstore.di.AppComponent;
import com.mipt.artem.cleandealstore.di.DaggerAppComponent;
import com.mipt.artem.cleandealstore.di.user.UserComponent;
import com.mipt.artem.cleandealstore.di.user.UserModule;
import com.mipt.artem.cleandealstore.model.User;

/**
 * Created by artem on 12.08.16.
 */
public class CleanDealStoreApplication  extends Application {
    private static AppComponent component;
    private static UserComponent userComponent;
    private static CleanDealStoreApplication context;



    public UserComponent createUserComponent(User user) {
        userComponent = component.plus(new UserModule(user));
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public static UserComponent getUserComponent() {
        return userComponent;
    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }

    public static CleanDealStoreApplication get() {
        return CleanDealStoreApplication.context;
    }

}
