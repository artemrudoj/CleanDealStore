package com.mipt.artem.cleandealstore.user.registration;

import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.model.AuthenticationService;
import com.mipt.artem.cleandealstore.model.UserController;
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
    protected UserController mUserController;

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
                mUserController.getCurrentUser().updateUser(userVO.getUser());
            }
        });
        mView.showLoading();
        addSubscription(subscription);
    }
}
