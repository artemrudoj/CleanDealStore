package com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartAdapter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBaseFragment;

import java.util.List;

import javax.inject.Inject;


public class OneTimeDeliveryInShoppingCartFragment extends ShoppingCartBaseFragment {

    @Inject
    OneTimeDeliveryPresenter mPresenter;

    public OneTimeDeliveryInShoppingCartFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getSwipeDirs() {
        return ItemTouchHelper.LEFT;
    }


    @Override
    protected ShoppingCartAdapter createAdapter(List<ItemInCart> data) {
        return new ShoppingCartAdapter(ItemInCart.filterByBasket(data, false), mPresenter);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter.onCreateView(savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void handleSuccessfulMoving() {
        Toast.makeText(getActivity(), "Перемещено", Toast.LENGTH_SHORT).show();
    }
}
