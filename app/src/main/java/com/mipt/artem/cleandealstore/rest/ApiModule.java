package com.mipt.artem.cleandealstore.rest;





import android.app.ProgressDialog;
import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.network.XSCRFInterceptor;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiModule {
    private static final boolean ENABLE_LOG = true;

    private static Context getContext() {
        //// TODO: 20/11/16
        return CleanDealStoreApplication.get();
    }


    private ApiModule() {
    }

    public static ApiInterface getApiInterface(String url) {

        OkHttpClient httpClient;

        CookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getContext()));
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .cookieJar(cookieJar);

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addNetworkInterceptor(interceptor);
        }


        okHttpClientBuilder.addInterceptor(new XSCRFInterceptor(cookieJar));
        httpClient = okHttpClientBuilder.build();



        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        return builder.build().create(ApiInterface.class);

    }


}
