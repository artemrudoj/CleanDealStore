package com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery;


import android.os.Bundle;

import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBaseFragment;


public class OneTimeDeliveryFragment extends ShoppingCartBaseFragment {


    public OneTimeDeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
    }
}
