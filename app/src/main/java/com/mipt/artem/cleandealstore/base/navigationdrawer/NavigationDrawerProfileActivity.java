package com.mipt.artem.cleandealstore.base.navigationdrawer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;
import com.mipt.artem.cleandealstore.base.NavigationDrawerController;
import com.mipt.artem.cleandealstore.base.OnBackPressedListener;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListFragment;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartContainerFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by artem on 22.05.16.
 */
public abstract class NavigationDrawerProfileActivity  extends BaseActivity
        implements  NavigationDrawerController, OnBackPressedListener.controller {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_bottom)
    BottomNavigationView mBottomNavigationView;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;


    private OnBackPressedListener.handler mBackPressedListener;

    @Override
    public void addOnBackPressedListener(OnBackPressedListener.handler listener) {
        mBackPressedListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_profile);
        ButterKnife.bind(this);
        initViews();
    }


    private void initViews() {
        mBottomNavigationView.inflateMenu(R.menu.bottom_navigtion_main);
        final Map<Integer, TapHandler> idActionMatcher = new HashMap<>();
        idActionMatcher.put(R.id.action_subscription, new TapHandler() {
            @Override
            public void onTap() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new CategoriesListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        idActionMatcher.put(R.id.action_categories, new TapHandler() {
            @Override
            public void onTap() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new CategoriesListFragment().newInstance(Category.ROOT))
                        .addToBackStack(null)
                        .commit();
            }
        });
        idActionMatcher.put(R.id.action_cart, new TapHandler() {
            @Override
            public void onTap() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ShoppingCartContainerFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                TapHandler tapHandler = idActionMatcher.get(item.getItemId());
                if (tapHandler != null) {
                    tapHandler.onTap();
                } else {
                    throw new IllegalArgumentException("can not find correct tap handler for id");
                }
                return true;
            }
        });
        setupDrawerContent(mNavigationView, idActionMatcher);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isEnabled()) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addHomeProfileButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        addActionBarDrawerToggle();
    }

    public void addActionBarDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView, final Map<Integer, TapHandler> idActionMatcher) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        TapHandler onTapListener = idActionMatcher.get(menuItem.getItemId());
                        onTapListener.onTap();
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void enableNavigationDrawer(boolean shouldEnable) {
        mDrawerLayout.setEnabled(shouldEnable);
        if (shouldEnable) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }


    @Override
    public void onBackPressed() {
        if (mBackPressedListener != null) {
            if (mBackPressedListener.handleBackPressed()) {
                return;
            }
        }
        android.app.FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
