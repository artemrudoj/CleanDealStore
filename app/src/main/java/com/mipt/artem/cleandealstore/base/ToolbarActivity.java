package com.mipt.artem.cleandealstore.base;

import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;


public class ToolbarActivity extends BaseActivity {

    public void setToolbar(int titleResourceId) {
        setToolbar(getString(titleResourceId));
    }

    public void setToolbar(String title) {
        android.support.v7.widget.Toolbar mActionBarToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("");
        TextView titleToolbar = (TextView) mActionBarToolbar.findViewById(R.id.title_tv);
        titleToolbar.setText(title);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void setToolbar() {
        setToolbar("");
    }

    public void changeTitle(String title) {
        android.support.v7.widget.Toolbar mActionBarToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        TextView titleToolbar = (TextView) mActionBarToolbar.findViewById(R.id.title_tv);
        titleToolbar.setText(title);
    }

    public void changeTitle(int title) {
        changeTitle(getString(title));
    }
}
