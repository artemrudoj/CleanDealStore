package com.mipt.artem.cleandealstore.base;

import android.app.Application;

import com.mipt.artem.cleandealstore.di.AppComponent;
import com.mipt.artem.cleandealstore.di.DaggerAppComponent;

/**
 * Created by artem on 12.08.16.
 */
public class CleanDealStoreApplication  extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
