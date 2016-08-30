package com.mipt.artem.cleandealstore;


import android.os.Bundle;

import com.mipt.artem.cleandealstore.base.navigationdrawer.NavigationDrawerProfileActivity;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListFragment;

public class MainActivity extends NavigationDrawerProfileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar("Категории");
        addHomeProfileButton();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new CategoriesListFragment()).commit();
        }
    }
}
