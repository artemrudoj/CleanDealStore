package com.mipt.artem.cleandealstore.base.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;


/**
 * Created by artem on 22.05.16.
 */
public abstract class NavigationDrawerProfileActivity  extends ToolbarActivity implements AdapterView.OnItemClickListener {

    DrawerLayout drawer;
    ListView recordsListView;
    NavigationDrawerListBaseAdapter adapter;
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
        recordsListView.setOnItemClickListener(this);
        adapter = createAdapter();

//        headerView = new NavigarionDrawerHeaderView(this);
//        headerView.setUser(getUser());
//        recordsListView.addHeaderView(headerView, null, false);

        recordsListView.setAdapter(adapter);
    }

    protected  NavigationDrawerListBaseAdapter createAdapter() {
        ArrayList<NavigationItem> items = new ArrayList<>();
        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));

        items.add(new NavigationItem(R.drawable.ic_accessible_black_24dp, R.string.open, false,false, new TapHandler() {
            @Override
            public void onTap() {

//                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));


        NavigationDrawerListBaseAdapter adapter = new NavigationDrawerListBaseAdapter(this, items);
        //adapter.registerDataSetObserver(adapter.getDataSetObserver());
        return adapter;
    }

    private void initViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        recordsListView = (ListView) findViewById(R.id.left_drawer);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        // for example client do not required to be login to see help section
        NavigationItem item = (NavigationItem)recordsListView.getAdapter().getItem(position);
        onItemClick(item.isLoginRequired(), item.isShouldCloseDrawer(), item);
    }

    protected void onItemClick(final boolean isLoginRequired,
                               boolean isShouldCloseDrawer, final TapHandler tapHandler) {
        if (isShouldCloseDrawer) {
            drawer.closeDrawer(Gravity.LEFT);
        }
        // use post delay to prevent perfomance issues with simultanious
        // activity start and navigation drawer closing
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (isLoginRequired && !isLogin()) {
//                    Intent intent = new Intent(NavigationDrawerProfileActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                } else {
//                    tapHandler.onTap();
//                }
            }
        }, 200);
    }
}
