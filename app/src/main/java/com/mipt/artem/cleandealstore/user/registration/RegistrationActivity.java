package com.mipt.artem.cleandealstore.user.registration;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;


import butterknife.Bind;
import butterknife.ButterKnife;


public class RegistrationActivity extends BaseActivity {
    @Bind(R.id.toolbar_actionbar)
    Toolbar mActionBarToolbar;

    public void setToolbar(int titleResourceId) {
        setToolbar(getString(titleResourceId));
    }

    public void setToolbar(String title) {
        mActionBarToolbar.setTitle(title);
        setSupportActionBar(mActionBarToolbar);
    }


    public static void goTo(Context context) {
        context.startActivity(new Intent(context, RegistrationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity);
        ButterKnife.bind(this);
        setToolbar(getString(R.string.registration));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (savedInstanceState == null) {
            Fragment fragment = new RegistrationNumberFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }
}