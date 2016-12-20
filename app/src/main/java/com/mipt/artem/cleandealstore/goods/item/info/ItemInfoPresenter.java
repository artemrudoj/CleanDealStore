package com.mipt.artem.cleandealstore.goods.item.info;

import com.mipt.artem.cleandealstore.base.BaseGoodsPresenter;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by artem on 20.08.16.
 */
public class ItemInfoPresenter  extends BaseGoodsPresenter {
    private ItemInfoView mItemInfoView;
    public ItemInfoPresenter(ItemInfoView mItemInfoView) {
        this.mItemInfoView = mItemInfoView;
    }

    public void AddToShoppingCart(Item item, int count) {
        Subscription subscription = mShoppingCart.addItem(item, count).subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {
//                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
//                mView.stopLoading();
            }

            @Override
            public void onNext(Object categories) {
//                mView.stopLoading();
//                mView.showData(categories);
            }
        });
        addSubscription(subscription);

    }
}
