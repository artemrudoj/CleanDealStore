package com.mipt.artem.cleandealstore.goods.item.list;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

/**
 * Created by artem on 20.08.16.
 */
public interface ItemsListView extends RecycledBaseView<Item>{
    void goToItemInfo(Item item);
}
