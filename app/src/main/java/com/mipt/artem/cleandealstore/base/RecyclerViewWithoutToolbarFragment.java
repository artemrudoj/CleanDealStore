package com.mipt.artem.cleandealstore.base;

import android.content.Context;
import android.view.*;

import com.mipt.artem.cleandealstore.base.recycledviews.RecycledBaseView;
import com.mipt.artem.cleandealstore.ui.ProgressBarBuilder;

/**
 * Created by artem on 20.12.16.
 */

public abstract class RecyclerViewWithoutToolbarFragment<T> extends NoToolbarFragment implements RecycledBaseView<T> {

    private ProgressBarBuilder mFragmentProgressBarHelper;

    protected void initProgressBarBuilder(android.view.View hidedView, android.view.View rootView, Context context) {
        mFragmentProgressBarHelper = new ProgressBarBuilder(hidedView,
                context, (ViewGroup) rootView);
    }

    @Override
    public void showLoading() {
        mFragmentProgressBarHelper.beginAnimation();
    }


    @Override
    public void stopLoading() {
        mFragmentProgressBarHelper.endAnimation();
    }
}
