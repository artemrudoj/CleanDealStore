package com.mipt.artem.cleandealstore.di.view;



import com.mipt.artem.cleandealstore.di.Const;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListPresenter;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoPresenter;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListPresenter;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListView;
import com.mipt.artem.cleandealstore.registration.RegistrationNumberFragment;
import com.mipt.artem.cleandealstore.registration.RegistrationNumberPresenter;
import com.mipt.artem.cleandealstore.registration.RegistrationNumberView;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBasePresenter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartView;
import com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery.OneTimeDeliveryPresenter;
import com.mipt.artem.cleandealstore.shoppingcart.subscription.SubscriptionInShoppingCartPresenter;
import com.mipt.artem.cleandealstore.subscription.SubscriptionPresenter;
import com.mipt.artem.cleandealstore.subscription.SubscriptionView;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private CategoriesListView mCategoriesListView;
    private ItemsListView mItemsListView;
    private ItemInfoView mItemInfoView;
    private ShoppingCartView mShoppingCartView;
    private SubscriptionView mSubscriptionView;
    private RegistrationNumberView mRegistrationNumberView;

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

    public ViewDynamicModule(RegistrationNumberFragment registrationNumberFragment) {
        this.mRegistrationNumberView = registrationNumberFragment;
    }


    @Provides
    RegistrationNumberPresenter provideRegistartionNumberPresenter() {
        return new RegistrationNumberPresenter(mRegistrationNumberView);
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
    OneTimeDeliveryPresenter provideOneTimeDeliveryPresenter() {
        return new OneTimeDeliveryPresenter(mShoppingCartView);
    }

    @Provides
    SubscriptionInShoppingCartPresenter provideSubscriptionInShoppingCartPresenter() {
        return new SubscriptionInShoppingCartPresenter(mShoppingCartView);
    }

    @Provides
    ItemInfoPresenter provideItemsInfoPresenter() {
        return new ItemInfoPresenter(mItemInfoView);
    }

}
