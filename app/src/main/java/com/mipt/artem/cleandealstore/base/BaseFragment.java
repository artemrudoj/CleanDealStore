package com.mipt.artem.cleandealstore.base;

import android.support.v4.app.Fragment;



public abstract class BaseFragment extends Fragment {

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

}

