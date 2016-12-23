package com.mipt.artem.cleandealstore.goods.category;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.base.CleanDealStoreApplication;
import com.mipt.artem.cleandealstore.goods.category.info.CategoryWithDetailInfoHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

public class CategoriesListPresenter extends BaseGoodsPresenter {


    private CategoriesListView mView;
    private Category mCategory;

    // for DI
    @Inject
    public CategoriesListPresenter() {
    }

    public CategoriesListPresenter(CategoriesListView view) {
        super();
        CleanDealStoreApplication.getComponent().inject(this);
        this.mView = view;
    }

    public void onCreateView(Bundle savedInstanceState, Category category) {
        mCategory = category;
        loadData();
    }

    private void loadData() {
        Integer categoryId = mCategory != null ? mCategory.getId() : null;
        Subscription subscription = model.getSubcategories(categoryId).subscribe(new Observer<List<Category>>() {
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

    public void clickCategory(Category category) {
        mView.goToCategory(category);
    }

    public void clickShowCategoryDetail(final CategoryWithDetailInfoHolder categoryWithDetailInfoHolder, final CategoriesListAdapter.ViewHolder holder) {
        if (!categoryWithDetailInfoHolder.isDetailShown()) {
            Category category = categoryWithDetailInfoHolder.getCategory();
            if (category.getContainsCategoriesCount() != 0 && category.getContainsItemsCount() == 0) {
                if (categoryWithDetailInfoHolder.getChildDirectories() != null) {
                    initHolderWithCategories(categoryWithDetailInfoHolder, holder, categoryWithDetailInfoHolder.getChildDirectories());
                } else {
                    Subscription subscription = model.getSubcategories(category.getId()).subscribe(new Observer<List<Category>>() {
                        @Override
                        public void onCompleted() {
//                    mView.stopLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
//                    mView.stopLoading();
                        }

                        @Override
                        public void onNext(List<Category> categories) {
                            initHolderWithCategories(categoryWithDetailInfoHolder, holder, categories);
                            categoryWithDetailInfoHolder.setChildDirectories(categories);
//                    mView.stopLoading();
//                    mView.showData(categories);
                        }
                    });
                    addSubscription(subscription);
                }
            } else if (category.getContainsCategoriesCount() == 0 && category.getContainsItemsCount() != 0) {
                if (categoryWithDetailInfoHolder.getItems() != null) {
                    initHolderWithItems(categoryWithDetailInfoHolder, holder, categoryWithDetailInfoHolder.getItems());} else {

                    Subscription subscription = model.getItem(null, category.getId()).subscribe(new Observer<ItemsHolder>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(ItemsHolder itemsHolder) {
                            initHolderWithItems(categoryWithDetailInfoHolder, holder, itemsHolder.getItems());
                            categoryWithDetailInfoHolder.setItems(itemsHolder.getItems());
                        }
                    });
                    addSubscription(subscription);
                }
            }
        } else {
            categoryWithDetailInfoHolder.setDetailShown(false);
            holder.recyclerView.setVisibility(View.GONE);
        }
    }

    private void initHolderWithItems(CategoryWithDetailInfoHolder categoryWithDetailInfoHolder, CategoriesListAdapter.ViewHolder holder, List<Item> items) {
        categoryWithDetailInfoHolder.setDetailShown(true);
        holder.initRecyclerViewByItems(items);
        holder.recyclerView.setVisibility(View.VISIBLE);
    }

    private void initHolderWithCategories(CategoryWithDetailInfoHolder categoryWithDetailInfoHolder,
                                          CategoriesListAdapter.ViewHolder holder, List<Category> categories){
        categoryWithDetailInfoHolder.setDetailShown(true);
        holder.initRecyclerView(categories);
        holder.recyclerView.setVisibility(View.VISIBLE);
    }

    public void clickItemShortInfo(Item item, ImageView image) {
        mView.goToItemInfo(item, image);
    }
}
