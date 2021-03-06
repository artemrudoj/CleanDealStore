package com.mipt.artem.cleandealstore.goods.item.list;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by artem on 20.08.16.
 */
public class ItemsListPresenter extends BaseGoodsPresenter {

    private ItemsListView mView;
    private Category mCategory;



    @Inject
    public ItemsListPresenter() {
    }

    public ItemsListPresenter(ItemsListView view) {
        super();
        CleanDealStoreApplication.getComponent().inject(this);
        this.mView = view;
    }

    public void onCreateView(Bundle savedInstanceState, Category category) {
        mCategory = category;
        loadData();
    }

    private void loadData() {
        Subscription subscription = model.getItem(null, mCategory.getId()).subscribe(new Observer<ItemsHolder>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(ItemsHolder holder) {
                mView.stopLoading();
                mView.showData(holder.getItems());
            }
        });
        mView.showLoading();
        addSubscription(subscription);
    }

    public void clickItem(Item item, View image) {
        mView.goToItemInfo(item, image);
    }
}
