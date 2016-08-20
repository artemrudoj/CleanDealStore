package com.mipt.artem.cleandealstore.goods.subcategory;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;
import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.ToolbarActivity;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SubCategoriesListFragment extends RecyclerViewBaseFragment<Subcategory> implements SubCategoriesListView  {

    private final static String EXTRA_CATEGORY = "SubCategoriesListFragment.EXTRA_CATEGORY";
    private SubCategoriesListAdapter mAdapter;
    @Inject
    SubCategoriesListPresenter mPresenter;

    @Bind(R.id.subcategories_rv)
    RecyclerView mSubcategoriesRecyclerView;

    private Category mCategory;

    public SubCategoriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
        mCategory = getCategotyFromArguments();

    }

    private Category getCategotyFromArguments() {
        Bundle bundle = getArguments();
        Category category;
        if (bundle.containsKey(EXTRA_CATEGORY)) {
            category = bundle.getParcelable(EXTRA_CATEGORY);
        } else {
            throw new IllegalArgumentException("can not found category");
        }
        return category;
    }


    public static BaseFragment newInstance(Category category) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(EXTRA_CATEGORY, category);
        SubCategoriesListFragment fragment = new SubCategoriesListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subcategories_list, container, false);
        ButterKnife.bind(this, view);

        initProgressBarBuilder(mSubcategoriesRecyclerView, view, getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mSubcategoriesRecyclerView.setLayoutManager(llm);


        mPresenter.onCreateView(savedInstanceState, mCategory);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ToolbarActivity) getActivity()).setToolbar(mCategory.getName());
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showData(List<Subcategory> data) {
        mAdapter = new SubCategoriesListAdapter(data, mPresenter);
        mSubcategoriesRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void goToSubcategory(Subcategory category) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,
                ItemsListFragment.newInstance(category)).commit();
    }
}
