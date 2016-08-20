package com.mipt.artem.cleandealstore.goods.category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.subcategory.SubCategoriesListFragment;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CategoriesListFragment extends RecyclerViewBaseFragment<Category> implements CategoriesListView {

    @Inject
    CategoriesListPresenter mPresenter;

    @Bind(R.id.categories_rv)
    RecyclerView mCategoriesRecyclerView;

    private CategoriesListAdapter mAdapter;
    public CategoriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        viewComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categories_list, container, false);
        ButterKnife.bind(this, view);

        initProgressBarBuilder(mCategoriesRecyclerView, view, getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mCategoriesRecyclerView.setLayoutManager(llm);


        mPresenter.onCreateView(savedInstanceState);

        return view;
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }


    @Override
    public void goToCategory(Category category) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,
                SubCategoriesListFragment.newInstance(category)).commit();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showData(List data) {
        mAdapter = new CategoriesListAdapter(data, mPresenter);
        mCategoriesRecyclerView.setAdapter(mAdapter);
    }
}
