package com.mipt.artem.cleandealstore.base.recycledviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.ui.ProgressBarBuilder;

/**
 * Created by artem on 20.08.16.
 */
abstract public class RecyclerViewBaseFragment<T> extends BaseFragment implements RecycledBaseView<T>{

    private ProgressBarBuilder mFragmentProgressBarHelper;

    protected void initProgressBarBuilder(View hidedView, View rootView, Context context) {
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
