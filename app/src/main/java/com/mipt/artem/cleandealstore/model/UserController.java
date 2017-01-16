package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.rest.ApiInterface;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by artem on 16.01.17.
 */

public class UserController {
    @Inject
    User mCurrentUser;

    private final Observable.Transformer schedulersTransformer;
    @Inject
    protected ApiInterface mApiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public UserController(ApiInterface apiInterface, Scheduler uiThread, Scheduler ioThread, User user) {
        this.mApiInterface = apiInterface;
        this.schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread).observeOn(uiThread);
        this.uiThread = uiThread;
        this.ioThread = ioThread;
        this.mCurrentUser = user;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    public Observable<User> changeUserInfo(String first_name,
                                           String last_name,
                                           String email,
                                           Integer sex) {
        return mApiInterface
                .changeUserInfo(first_name,
                        last_name,
                        email,
                        sex)
                .compose(this.applySchedulers());
    }

    public Observable<Object> logout() {
        return mApiInterface
                .logout()
                .compose(this.applySchedulers());
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }
}
