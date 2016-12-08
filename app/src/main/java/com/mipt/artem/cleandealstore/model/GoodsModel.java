package com.mipt.artem.cleandealstore.model;

import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.rest.ApiInterface;
import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GoodsModel {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    protected ShoppingCart mShoppingCart;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public GoodsModel() {
        CleanDealStoreApplication.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread);
    }



    public Observable<List<Category>> getSubcategories(Integer id) {
        return apiInterface
                .getSubcategories(id)
                .compose(applySchedulers());
    }


    public Observable<ItemsHolder> getItem(String name, Integer id) {
        return apiInterface
                .getItems(name, id)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
