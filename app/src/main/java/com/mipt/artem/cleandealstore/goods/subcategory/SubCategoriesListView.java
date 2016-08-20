package com.mipt.artem.cleandealstore.goods.subcategory;

/**
 * Created by artem on 20.08.16.
 */

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

public interface  SubCategoriesListView extends RecycledBaseView<Subcategory>{
    void goToSubcategory(Subcategory category);
}
