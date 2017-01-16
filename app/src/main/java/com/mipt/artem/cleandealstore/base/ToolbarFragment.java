package com.mipt.artem.cleandealstore.base;

import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.navigationdrawer.NavigationDrawerProfileActivity;

import butterknife.Bind;

/**
 * Created by artem on 20.12.16.
 */

public abstract class ToolbarFragment extends BaseFragmentWithDrawer {
    @Bind(R.id.toolbar_actionbar)
    Toolbar mActionBarToolbar;

    public void setToolbar(int titleResourceId) {
        setToolbar(getString(titleResourceId));
    }

    public void setToolbar(String title) {
        mActionBarToolbar.setTitle(title);
        NavigationDrawerProfileActivity appCompatActivity = (NavigationDrawerProfileActivity) getActivity();
        appCompatActivity.setSupportActionBar(mActionBarToolbar);
        appCompatActivity.addHomeProfileButton();
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        enableDrawer(true);
    }
}
