package com.mipt.artem.cleandealstore.registration;

import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.model.AuthenticationService;
import com.mipt.artem.cleandealstore.network.UserVO;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by artem on 14.01.17.
 */

public class RegistrationNumberPresenter implements Presenter {

    @Inject
    protected AuthenticationService mAuthenticationService;

    @Inject
    protected CompositeSubscription compositeSubscription;

    private RegistrationNumberView mView;

    public RegistrationNumberPresenter(RegistrationNumberView registrationNumberView) {
        CleanDealStoreApplication.getComponent().inject(this);
        mView = registrationNumberView;
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public RegistrationNumberPresenter() {
        CleanDealStoreApplication.getComponent().inject(this);
    }

    public void getConfirmationCode(String phone) {
        Subscription subscription = mAuthenticationService.getConfirmationCode(phone).subscribe(new Observer<UserVO>() {
            @Override
            public void onCompleted() {
                mView.successGetCode();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(UserVO isNewUser) {
                mView.stopLoading();
            }
        });
//        Subscription subscription = mAuthenticationService.logout().subscribe(new Observer<Object>() {
//            @Override
//            public void onCompleted() {
//                mView.stopLoading();
//                mView.successGetCode();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mView.stopLoading();
//            }
//
//            @Override
//            public void onNext(Object obj) {
//                mView.stopLoading();
//            }
//        });
        mView.showLoading();
        addSubscription(subscription);

    }

    public void loginByConfirmationCode(String phone, String code) {
        Subscription subscription = mAuthenticationService.loginAndRegistrationByCode(code, phone)
                .subscribe(new Observer<UserVO>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(UserVO userVO) {
                mView.stopLoading();
                mView.successLoginByCode(userVO);
            }
        });
        mView.showLoading();
        addSubscription(subscription);
    }
}
