package com.mipt.artem.cleandealstore.goods.item.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.ToolbarActivity;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoActivity;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItemsListFragment extends RecyclerViewBaseFragment<Item> implements ItemsListView {

    private static final String EXTRA_SUBCATEGORY = "ItemsListFragment.EXTRA_SUBCATEGORY";

    @Inject
    ItemsListPresenter mPresenter;

    @Bind(R.id.items_rv)
    RecyclerView mItemsRecyclerView;

    private Subcategory mSubcategory;
    private ItemsAdapter mAdapter;

    public ItemsListFragment() {
        // Required empty public constructor
    }


    public static ItemsListFragment newInstance(Subcategory subcategory) {
        ItemsListFragment fragment = new ItemsListFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_SUBCATEGORY, subcategory);
        fragment.setArguments(args);
        return fragment;
    }

    private Subcategory getSubCategotyFromArguments() {
        Bundle bundle = getArguments();
        Subcategory subcategory;
        if (bundle.containsKey(EXTRA_SUBCATEGORY)) {
            subcategory = bundle.getParcelable(EXTRA_SUBCATEGORY);
        } else {
            throw new IllegalArgumentException("can not found category");
        }
        return subcategory;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
        mSubcategory = getSubCategotyFromArguments();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((ToolbarActivity) getActivity()).setToolbar(mSubcategory.getName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);

        initProgressBarBuilder(mItemsRecyclerView, view, getActivity());

        LinearLayoutManager llm = new GridLayoutManager(getContext(), 2);
        mItemsRecyclerView.setLayoutManager(llm);


        mPresenter.onCreateView(savedInstanceState, mSubcategory);
        return view;
    }


    @Override
    protected Presenter getPresenter() {
        return null;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showData(List<Item> data) {
        mAdapter = new ItemsAdapter(data, mPresenter);
        mItemsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void goToItemInfo(Item item) {
        BaseActivity activity = (BaseActivity) getActivity();
        ItemInfoActivity.goTo(item, activity);
    }
}
