package com.mipt.artem.cleandealstore.shoppingcart.subscription;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartAdapter;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartBaseFragment;

import java.util.List;

import javax.inject.Inject;

public class SubscriptionFragment extends ShoppingCartBaseFragment {

    @Inject
    SubscriptionInShoppingCartPresenter mPresenter;


    public SubscriptionFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getSwipeDirs() {
        return ItemTouchHelper.RIGHT;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setEmptyTextViewText(getString(R.string.emty_list_in_subscription));
        mPresenter.onCreateView(savedInstanceState);
        return view;
    }

    @Override
    protected ShoppingCartAdapter createAdapter(List<ItemInCart> data) {
        return new ShoppingCartAdapter(ItemInCart.filterByBasket(data, true), mPresenter);
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
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void handleSuccessfulMoving() {
        Toast.makeText(getActivity(), "Перемещено", Toast.LENGTH_SHORT).show();
    }
}
