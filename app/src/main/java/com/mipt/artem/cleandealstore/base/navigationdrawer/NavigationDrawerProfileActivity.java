package com.mipt.artem.cleandealstore.base.navigationdrawer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
        implements AdapterView.OnItemClickListener, NavigationDrawerController {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    ListView mRecordsListView;
    @Bind(R.id.navigation_bottom)
    BottomNavigationView mBottomNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationDrawerListBaseAdapter mNavigationDrawerAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_profile);
        ButterKnife.bind(this);
        initViews();


    }


    protected  NavigationDrawerListBaseAdapter createAdapter(Menu menu, Map<Integer, TapHandler> idActionMatcher) {

        ArrayList<NavigationItem> items = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            TapHandler tapHandler = idActionMatcher.get(item.getItemId());
            if (tapHandler == null) {
                throw new IllegalArgumentException("can not find correct tap handler for id");
            }
            items.add(new NavigationItem(item.getIcon(), item.getTitle().toString(), false, false, tapHandler));
        }
        NavigationDrawerListBaseAdapter adapter = new NavigationDrawerListBaseAdapter(this, items);
        return adapter;
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
//                    setToolbar(item.getTitle().toString());
                } else {
                    throw new IllegalArgumentException("can not find correct tap handler for id");
                }
                return true;
            }
        });

        mRecordsListView.setOnItemClickListener(this);

        Menu menu = mBottomNavigationView.getMenu();
        mNavigationDrawerAdapter = createAdapter(menu, idActionMatcher);
        mRecordsListView.setAdapter(mNavigationDrawerAdapter);

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        // for example client do not required to be login to see help section
        NavigationItem item = (NavigationItem) mRecordsListView.getAdapter().getItem(position);
        item.onTap();
        mRecordsListView.setItemChecked(position, true);
        if (item.isShouldCloseDrawer()) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        setTitle(item.getText());
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
        android.app.FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
