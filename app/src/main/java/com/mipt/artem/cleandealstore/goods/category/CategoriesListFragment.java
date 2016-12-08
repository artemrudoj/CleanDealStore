package com.mipt.artem.cleandealstore.goods.category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.ToolbarActivity;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsListFragment;
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

    private final static String EXTRA_CATEGORY = "SubCategoriesListFragment.EXTRA_CATEGORY";
    public static final int ROOT_CATEGORY = -1;

    private CategoriesListAdapter mAdapter;
    private Category mCategory;

    public CategoriesListFragment() {
        // Required empty public constructor
    }


    public static CategoriesListFragment newInstance(Category category) {
        CategoriesListFragment fragment = new CategoriesListFragment();
        if (category != Category.ROOT) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_CATEGORY, category);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private Category getCategoryFromArguments() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EXTRA_CATEGORY)) {
            return bundle.getParcelable(EXTRA_CATEGORY);
        } else {
            return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        viewComponent.inject(this);
        mCategory = getCategoryFromArguments();
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


        mPresenter.onCreateView(savedInstanceState, mCategory);

        return view;
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }


    @Override
    public void goToCategory(Category category) {
        Fragment fragment;
        if (category.getContainsCategoriesCount() != 0) {
            fragment = CategoriesListFragment.newInstance(category);
        } else {
            fragment = ItemsListFragment.newInstance(category);
        }
        getFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showData(List data) {
        mAdapter = new CategoriesListAdapter(data, mPresenter);
        mCategoriesRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCategory != null) {
            ((ToolbarActivity) getActivity()).setToolbar(mCategory.getName());
        }
    }
}
