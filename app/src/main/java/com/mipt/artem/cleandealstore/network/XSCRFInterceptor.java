package com.mipt.artem.cleandealstore.network;

import android.util.Log;

import com.mipt.artem.cleandealstore.di.Const;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.prefs.Preferences;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by artem on 16.01.17.
 */

public class XSCRFInterceptor implements Interceptor {

    CookieJar mCookieJar;

    public XSCRFInterceptor(CookieJar cookieJar) {
        this.mCookieJar = cookieJar;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String csrf = getCsrf(mCookieJar, chain.request().url());
        if (csrf != null) {
            builder.addHeader("X-CSRFToken", csrf);
        }
        Request request = builder.build();

        return chain.proceed(request);
    }


    private static String getCsrf(CookieJar cookieJar, HttpUrl url ) {
        List<Cookie> cookies = cookieJar.loadForRequest(url);
        for (Cookie cookie : cookies) {
            if (cookie.name().equals("csrftoken")) {
                return cookie.value();
            }
        }
        return null;
    }
}
