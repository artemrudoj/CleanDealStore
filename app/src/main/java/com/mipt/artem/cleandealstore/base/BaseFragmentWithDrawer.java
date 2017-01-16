package com.mipt.artem.cleandealstore.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;


public abstract class BaseFragmentWithDrawer extends BaseFragment {


    private NavigationDrawerController mController;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationDrawerController) {
            mController = (NavigationDrawerController) context;
        } else {
            throw new IllegalArgumentException("activity should implement ToolbarController");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mController = null;
    }

    protected void enableDrawer(boolean isEnable) {
         if (mController != null) {
             mController.enableNavigationDrawer(isEnable);
         }
    }
}

