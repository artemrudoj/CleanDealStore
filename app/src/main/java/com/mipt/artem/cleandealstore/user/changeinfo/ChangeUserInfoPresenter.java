package com.mipt.artem.cleandealstore.user.changeinfo;

import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.model.User;
import com.mipt.artem.cleandealstore.model.UserController;


import javax.inject.Inject;


import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by artem on 16.01.17.
 */

public class ChangeUserInfoPresenter implements Presenter {


    @Inject
    protected UserController mUserController;



    @Inject
    protected CompositeSubscription compositeSubscription;

    private ChangeUserInfoView mView;

    public ChangeUserInfoPresenter(ChangeUserInfoView view) {
        CleanDealStoreApplication.getComponent().inject(this);
        mView = view;
    }



    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }


    public void changeUserInfo(String first_name,
                                           String last_name,
                                           String email,
                                           Integer sex) {
        Subscription subscription = mUserController.changeUserInfo(first_name,
                last_name,
                email,
                sex).subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                mView.successChanged();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(User isNewUser) {
                mView.stopLoading();
            }
        });
        mView.showLoading();
        addSubscription(subscription);
    }

    public User getCurrentUser() {
        return mUserController.getCurrentUser();
    }
}
