package com.mipt.artem.cleandealstore.shoppingcart;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListAdapter;
import com.mipt.artem.cleandealstore.model.ItemInCart;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends RecyclerViewBaseFragment<ItemInCart> implements ShoppingCartView{


    @Inject
    ShoppingCartPresenter mPresenter;

    @Bind(R.id.goods_rv)
    RecyclerView mShoppingCartRecyclerView;

    private ShoppingCartAdapter mAdapter;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        ButterKnife.bind(this, view);

        initProgressBarBuilder(mShoppingCartRecyclerView, view, getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mShoppingCartRecyclerView.setLayoutManager(llm);


        mPresenter.onCreateView(savedInstanceState);
        return view;
    }


    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showData(List<ItemInCart> data) {
        mAdapter = new ShoppingCartAdapter(data, mPresenter);
        mShoppingCartRecyclerView.setAdapter(mAdapter);
    }
}
