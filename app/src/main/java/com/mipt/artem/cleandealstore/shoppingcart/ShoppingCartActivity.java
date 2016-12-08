package com.mipt.artem.cleandealstore.shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.navigationdrawer.NavigationDrawerProfileActivity;

public class ShoppingCartActivity extends NavigationDrawerProfileActivity {

    public static void goTo(Activity activity) {
        Intent intent = new Intent(activity, ShoppingCartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.shoppingCart);
        addHomeProfileButton();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ShoppingCartContainerFragment()).commit();
        }
    }

}
