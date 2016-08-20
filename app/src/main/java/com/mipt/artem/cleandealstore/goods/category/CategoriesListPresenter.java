package com.mipt.artem.cleandealstore.goods.category;

import android.os.Bundle;


import com.mipt.artem.cleandealstore.base.BasePresenter;
import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

public class CategoriesListPresenter extends BasePresenter {


    private CategoriesListView mView;


    // for DI
    @Inject
    public CategoriesListPresenter() {
    }

    public CategoriesListPresenter(CategoriesListView view) {
        super();
        CleanDealStoreApplication.getComponent().inject(this);
        this.mView = view;
    }

    public void onCreateView(Bundle savedInstanceState) {
        loadData();
    }

    private void loadData() {
       Subscription subscription = model.getCategories().subscribe(new Observer<List<Category>>() {
           @Override
           public void onCompleted() {
               mView.stopLoading();
           }

           @Override
           public void onError(Throwable e) {
               mView.stopLoading();
           }

           @Override
           public void onNext(List<Category> categories) {
               mView.stopLoading();
               mView.showData(categories);
           }
       });
        mView.showLoading();
        addSubscription(subscription);
    }

    public void clickRepo(Category category) {
        mView.goToCategory(category);
    }
}
