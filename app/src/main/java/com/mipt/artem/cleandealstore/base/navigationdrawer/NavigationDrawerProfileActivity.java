package com.mipt.artem.cleandealstore.base.navigationdrawer;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;


import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.ToolbarActivity;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListContainerActivity;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartActivity;
import com.mipt.artem.cleandealstore.ui.BottomNavigationController;

import java.util.ArrayList;




/**
 * Created by artem on 22.05.16.
 */
public abstract class NavigationDrawerProfileActivity  extends ToolbarActivity implements AdapterView.OnItemClickListener {

    DrawerLayout mDrawerLayout;

    ListView mRecordsListView;


    NavigationDrawerListBaseAdapter mNavigationDrawerAdapter;

    BottomNavigationView mBottomNavigationView;
   // protected NavigarionDrawerHeaderView headerView;


    @Override
    protected void onResume() {
        super.onResume();
//        headerView.updateView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_profile);
        initViews();
        initListView();
    }

    private void initListView() {
        mRecordsListView.setOnItemClickListener(this);
        mNavigationDrawerAdapter = createAdapter();

//        headerView = new NavigarionDrawerHeaderView(this);
//        headerView.setUser(getUser());
//        mRecordsListView.addHeaderView(headerView, null, false);

        mRecordsListView.setAdapter(mNavigationDrawerAdapter);
    }

    protected  NavigationDrawerListBaseAdapter createAdapter() {
        ArrayList<NavigationItem> items = new ArrayList<>();
        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.categories, false,false, new TapHandler() {
            @Override
            public void onTap() {
                if (!(NavigationDrawerProfileActivity.this instanceof CategoriesListContainerActivity)) {
                    CategoriesListContainerActivity.goTo(NavigationDrawerProfileActivity.this);
                }
            }
        }));
        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.shoppingCart, false,false, new TapHandler() {
            @Override
            public void onTap() {
                if (!(NavigationDrawerProfileActivity.this instanceof ShoppingCartActivity)) {
                    ShoppingCartActivity.goTo(NavigationDrawerProfileActivity.this);;
                }
            }
        }));


        NavigationDrawerListBaseAdapter adapter = new NavigationDrawerListBaseAdapter(this, items);
        //mNavigationDrawerAdapter.registerDataSetObserver(mNavigationDrawerAdapter.getDataSetObserver());
        return adapter;
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecordsListView = (ListView) findViewById(R.id.left_drawer);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom) ;
        BottomNavigationController controller = new BottomNavigationController(mBottomNavigationView);
        controller.init();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        // for example client do not required to be login to see help section
        NavigationItem item = (NavigationItem) mRecordsListView.getAdapter().getItem(position);
        onItemClick(item.isLoginRequired(), item.isShouldCloseDrawer(), item);
    }

    protected void onItemClick(final boolean isLoginRequired,
                               boolean isShouldCloseDrawer, final TapHandler tapHandler) {
        if (isShouldCloseDrawer) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        // use post delay to prevent perfomance issues with simultanious
        // activity start and navigation mDrawerLayout closing
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (isLoginRequired && !isLogin()) {
//                    Intent intent = new Intent(NavigationDrawerProfileActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                } else {
                    tapHandler.onTap();
//                }
            }
        }, 200);
    }
}
