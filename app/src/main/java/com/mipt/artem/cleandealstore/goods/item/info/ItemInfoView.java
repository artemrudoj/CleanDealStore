package com.mipt.artem.cleandealstore.goods.item.info;

/**
 * Created by artem on 20.08.16.
 */
public interface ItemInfoView {
    void goToShoppingCart(boolean isInSubscription);

    void successfullyAddedOnSubscription();
    void successfullyAddedOnOneTimeDelivery();
}
