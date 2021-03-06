package com.mipt.artem.cleandealstore.di.view;

import com.mipt.artem.cleandealstore.goods.category.CategoriesListFragment;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
import com.mipt.artem.cleandealstore.user.changeinfo.ChangeUserInfoFragment;
import com.mipt.artem.cleandealstore.user.registration.RegistrationNumberFragment;
import com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery.OneTimeDeliveryInShoppingCartFragment;
import com.mipt.artem.cleandealstore.shoppingcart.subscription.SubscriptionFragment;
import com.mipt.artem.cleandealstore.subscription.SubscriptionsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(CategoriesListFragment repoListFragment);

    void inject(ItemsListFragment itemsFragment);

    void inject(ItemInfoFragment itemInfoFragment);

    void inject(SubscriptionsFragment subscriptionsFragment);

    void inject(OneTimeDeliveryInShoppingCartFragment oneTimeDeliveryInShoppingCartFragment);

    void inject(SubscriptionFragment subscriptionFragment);

    void inject(RegistrationNumberFragment registrationNumberFragment);

    void inject(ChangeUserInfoFragment changeUserInfoFragment);
}
