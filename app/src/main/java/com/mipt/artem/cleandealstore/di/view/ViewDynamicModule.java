package com.mipt.artem.cleandealstore.di.view;



import com.mipt.artem.cleandealstore.goods.category.CategoriesListPresenter;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoView;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoPresenter;
import com.mipt.artem.cleandealstore.goods.subcategory.SubCategoriesListFragment;
import com.mipt.artem.cleandealstore.goods.subcategory.SubCategoriesListPresenter;
import com.mipt.artem.cleandealstore.goods.subcategory.SubCategoriesListView;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListPresenter;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private CategoriesListView mCategoriesListView;
    private SubCategoriesListView mSubCategoriesListView;
    private ItemsListView mItemsListView;
    private ItemInfoView mItemInfoView;

    public ViewDynamicModule(CategoriesListView view) {
        this.mCategoriesListView = view;
    }

    public ViewDynamicModule(SubCategoriesListFragment view) {
        this.mSubCategoriesListView = view;
    }

    public ViewDynamicModule(ItemsListFragment view) {
        this.mItemsListView = view;
    }

    public ViewDynamicModule(ItemInfoFragment view) {
        this.mItemInfoView = view;
    }

    @Provides
    SubCategoriesListPresenter provideSubCategoriesListPresenter() {
        return new SubCategoriesListPresenter(mSubCategoriesListView);
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
    ItemInfoPresenter provideItemsInfoPresenter() {
        return new ItemInfoPresenter(mItemInfoView);
    }

}
