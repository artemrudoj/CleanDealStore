package com.mipt.artem.cleandealstore.di.view;

import com.mipt.artem.cleandealstore.goods.category.CategoriesListFragment;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.goods.subcategory.SubCategoriesListFragment;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(CategoriesListFragment repoListFragment);

    void inject(SubCategoriesListFragment subCategoriesListFragment);

    void inject(ItemsListFragment itemsFragment);

    void inject(ItemInfoFragment itemInfoFragment);
}
