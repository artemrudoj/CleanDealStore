package com.mipt.artem.cleandealstore.shoppingcart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.base.NoToolbarFragment;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.ToolbarFragment;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.shoppingcart.onetimedelivery.OneTimeDeliveryInShoppingCartFragment;
import com.mipt.artem.cleandealstore.shoppingcart.subscription.SubscriptionFragment;
import com.mipt.artem.cleandealstore.ui.CustomFragmentPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartContainerFragment extends ToolbarFragment implements ItemsUpdateListener {

    private PagerAdapter mAdapter;
    public static final String IS_OPEN_SUBSCRIPTION = "ShoppingCartContainerFragment.IS_OPEN_SUBSCRIPTION";

    @Bind(R.id.pager)
    ViewPager mViewPager;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;


    boolean isOpenSubscription = true;

    public ShoppingCartContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        ButterKnife.bind(this, view);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.text_subscription));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.one_time_delivery));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setToolbar(getString(R.string.trash));
        enableDrawer(true);

        mAdapter = new PagerAdapter
                (getChildFragmentManager(), R.id.pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //go to one time delivery
        if (!isOpenSubscription) {
            mViewPager.setCurrentItem(1);
        }
        return view;
    }

    @Override
    public void onUpdateItems(List<ItemInCart> items) {

        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            Fragment fragment = mAdapter.getItem(i);
            if (fragment instanceof ItemsUpdateListener) {
                ((ItemsUpdateListener)fragment).onUpdateItems(items);
            }
        }
    }

    @Override
    protected Presenter getPresenter() {
        return null;
    }

    public static ShoppingCartContainerFragment newInstance(boolean isInSubscription) {
        ShoppingCartContainerFragment fragment = new ShoppingCartContainerFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_OPEN_SUBSCRIPTION, isInSubscription);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            isOpenSubscription = args.getBoolean(IS_OPEN_SUBSCRIPTION, true);
        }
    }

    public class PagerAdapter extends CustomFragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm, int containerId) {
            super(fm,containerId);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = getFragmentByPosition(position);
            if (fragment != null) {
                return fragment;
            }
            switch (position) {
                case 0:
                    SubscriptionFragment tab1 = new SubscriptionFragment();
                    return tab1;
                case 1:
                    OneTimeDeliveryInShoppingCartFragment tab2 = new OneTimeDeliveryInShoppingCartFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
