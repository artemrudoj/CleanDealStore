package com.mipt.artem.cleandealstore.di.view;



import com.mipt.artem.cleandealstore.goods.category.CategoriesListPresenter;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoPresenter;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListPresenter;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListView;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartPresenter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartView;
import com.mipt.artem.cleandealstore.subscription.SubscriptionPresenter;
import com.mipt.artem.cleandealstore.subscription.SubscriptionView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private CategoriesListView mCategoriesListView;
    private ItemsListView mItemsListView;
    private ItemInfoView mItemInfoView;
    private ShoppingCartView mShoppingCartView;
    private SubscriptionView mSubscriptionView;

    public ViewDynamicModule(CategoriesListView view) {
        this.mCategoriesListView = view;
    }

    public ViewDynamicModule(ItemsListFragment view) {
        this.mItemsListView = view;
    }

    public ViewDynamicModule(ItemInfoFragment view) {
        this.mItemInfoView = view;
    }

    public ViewDynamicModule(ShoppingCartView view) {
        this.mShoppingCartView = view;
    }

    public ViewDynamicModule(SubscriptionView view) {
        this.mSubscriptionView = view;
    }


    @Provides
    CategoriesListPresenter provideCategoriesListPresenter() {
        return new CategoriesListPresenter(mCategoriesListView);
    }

    @Provides
    ItemsListPresenter provideItemsListPresenter() {
        return new ItemsListPresenter(mItemsListView);
    }

    @Provides
    SubscriptionPresenter provideSubscriptionPresenter() {
        return new SubscriptionPresenter(mSubscriptionView);
    }

    @Provides
    ShoppingCartPresenter provideShoppingCartPresenter() {
        return new ShoppingCartPresenter(mShoppingCartView);
    }

    @Provides
    ItemInfoPresenter provideItemsInfoPresenter() {
        return new ItemInfoPresenter(mItemInfoView);
    }

}
