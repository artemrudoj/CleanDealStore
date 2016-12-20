package com.mipt.artem.cleandealstore.goods.category;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.navigationdrawer.NavigationDrawerProfileActivity;

public class CategoriesListContainerActivity extends NavigationDrawerProfileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new CategoriesListFragment()).commit();
        }
    }


    public static void goTo(Activity activity) {
        Intent intent = new Intent(activity, CategoriesListContainerActivity.class);
        activity.startActivity(intent);
    }
}
