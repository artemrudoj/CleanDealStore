package com.mipt.artem.cleandealstore.goods.subcategory;

import android.os.Bundle;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by artem on 20.08.16.
 */
public class SubCategoriesListPresenter extends BaseGoodsPresenter {

    private SubCategoriesListView mView;
    private Category mCategory;


    // for DI
    @Inject
    public SubCategoriesListPresenter() {
    }

    public SubCategoriesListPresenter(SubCategoriesListView view) {
        super();
        CleanDealStoreApplication.getComponent().inject(this);
        this.mView = view;
    }

    public void onCreateView(Bundle savedInstanceState, Category category) {
        mCategory = category;
        loadData();
    }

    private void loadData() {
        Subscription subscription = model.getSubcategories(mCategory.getId()).subscribe(new Observer<List<Subcategory>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(List<Subcategory> subcategories) {
                mView.stopLoading();
                mView.showData(subcategories);
            }
        });
        mView.showLoading();
        addSubscription(subscription);
    }


    public void clickSubCategory(Subcategory category) {
        mView.goToSubcategory(category);
    }
}
