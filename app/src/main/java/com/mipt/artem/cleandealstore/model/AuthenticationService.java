package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.network.UserVO;
import com.mipt.artem.cleandealstore.rest.ApiInterface;

import java.io.ObjectStreamException;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Completable;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by artem on 16.01.17.
 */

public class AuthenticationService {
    private final Observable.Transformer schedulersTransformer;
    @Inject
    protected ApiInterface mApiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public AuthenticationService(ApiInterface apiInterface, Scheduler uiThread, Scheduler ioThread) {
        this.mApiInterface = apiInterface;
        this.schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread).observeOn(uiThread);
        this.uiThread = uiThread;
        this.ioThread = ioThread;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }


    public Observable<UserVO> getConfirmationCode(String phone) {
        return mApiInterface
                .getConfirmationCode(phone)
                .compose(this.applySchedulers());
    }

    public Observable<UserVO> loginAndRegistrationByCode(String code, String phone) {
        return mApiInterface
                .loginAndRegistrationByCode(code, phone, null, null, null)
                .compose(this.applySchedulers());
    }

    public Observable<Object> logout() {
        return mApiInterface
                .logout()
                .compose(this.applySchedulers());
    }
}
