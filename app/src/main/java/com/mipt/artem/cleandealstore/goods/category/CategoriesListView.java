package com.mipt.artem.cleandealstore.goods.category;

import android.widget.ImageView;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

/**
 * Created by artem on 15.08.16.
 */
public interface CategoriesListView extends RecycledBaseView<Category> {
    void goToCategory(Category category);

    void goToItemInfo(Item item, ImageView image);
}
