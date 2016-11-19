package com.mipt.artem.cleandealstore.ui;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListContainerActivity;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartActivity;

/**
 * Created by artem on 20.11.16.
 */

public class BottomNavigationController {
    private BottomNavigationView mBottomNavigationView;


    public BottomNavigationController(BottomNavigationView mBottomNavagationView) {
        this.mBottomNavigationView = mBottomNavagationView;
        Activity activity = getActivity();
        if (activity == null ) return;
//        if (activity instanceof CategoriesListContainerActivity) {
//            mBottomNavigationView.setActiv
//        }
    }

    public void init() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Activity activity = getActivity();
                if (activity == null) {
                    return false;
                }
                switch (item.getItemId()) {
                    case R.id.action_subscription:

                        break;
                    case R.id.action_categories:
                        CategoriesListContainerActivity.goTo(activity);
                        break;
                    case R.id.action_cart:
                        ShoppingCartActivity.goTo(activity);
                        break;
                }
                return true;
            }
        });
    }


    private Activity getActivity() {
        if (mBottomNavigationView.getContext() instanceof  Activity) {
            return (Activity) mBottomNavigationView.getContext();
        } return null;
    }
}
