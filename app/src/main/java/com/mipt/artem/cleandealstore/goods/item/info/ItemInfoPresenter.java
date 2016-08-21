package com.mipt.artem.cleandealstore.goods.item.info;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

/**
 * Created by artem on 20.08.16.
 */
public class ItemInfoPresenter  extends BaseGoodsPresenter {
    private ItemInfoView mItemInfoView;
    public ItemInfoPresenter(ItemInfoView mItemInfoView) {
        this.mItemInfoView = mItemInfoView;
    }

    public void AddToShoppingCart(Item item, int count) {
        mShoppingCart.addItem(item, count);
    }
}
