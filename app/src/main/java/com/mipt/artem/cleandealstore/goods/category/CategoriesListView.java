package com.mipt.artem.cleandealstore.goods.category;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

/**
 * Created by artem on 15.08.16.
 */
public interface CategoriesListView extends RecycledBaseView<Category> {
    void goToCategory(Category category);
}
