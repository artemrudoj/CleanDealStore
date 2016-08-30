package com.mipt.artem.cleandealstore.shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BackButtonActivity;
import com.mipt.artem.cleandealstore.base.ToolbarActivity;
import com.mipt.artem.cleandealstore.subscription.SubscriptionsFragment;

public class ShoppingCartActivity extends BackButtonActivity {

    public static void goTo(Activity activity) {
        Intent intent = new Intent(activity, ShoppingCartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setToolbar(R.string.shoppingCart);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ShoppingCartFragment()).commit();
        }
    }
}
