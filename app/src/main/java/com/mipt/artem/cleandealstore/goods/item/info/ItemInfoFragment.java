package com.mipt.artem.cleandealstore.goods.item.info;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.Utils;
import com.mipt.artem.cleandealstore.base.NoToolbarFragment;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.rest.responcedata.ExtraInfo;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.shoppingcart.ShoppingCartActivity;
import com.squareup.picasso.Picasso;



import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemInfoFragment extends NoToolbarFragment implements ItemInfoView, View.OnClickListener {
    private static final String TAG = "ItemInfoFragment";
    private Item mItem;
    @Inject
    ItemInfoPresenter mPresenter;

    @Bind(R.id.item_image_iv)
    ImageView mItemImageView;

    @Bind(R.id.additional_info_lv)
    ListView mAdditionalInfoListView;

    @Bind(R.id.price_item_tv)
    TextView mPriceItemTextView;

    @Bind(R.id.name_item_tv)
    TextView mNameItemTextView;

    @Bind(R.id.collapsed_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.about_tv)
    TextView mAboutTextView;


    @Bind(R.id.add_to_trash_fab)
    FloatingActionButton mAddToShoppingFloatingActionButton;

    @Bind(R.id.app_bl)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.collapse_tl)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    public ItemInfoFragment() {
        // Required empty public constructor
    }

    public static ItemInfoFragment newInstance(Item item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Item.EXTRA_ITEM, item);
        ItemInfoFragment fragment = new ItemInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
        mItem = Item.getItemFromBundle(getArguments());
        Transition sharedElementEnterTransition = (Transition) getSharedElementEnterTransition();
        if (sharedElementEnterTransition != null) {
            sharedElementEnterTransition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(@NonNull Transition transition) {
                    Log.d(TAG, "onTransitionStart: ");
                    mAddToShoppingFloatingActionButton.setVisibility(View.INVISIBLE);
                    mNameItemTextView.setVisibility(View.INVISIBLE);
                    mAboutTextView.setVisibility(View.INVISIBLE);
                    mPriceItemTextView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onTransitionEnd(@NonNull Transition transition) {
                    Log.d(TAG, "onTransitionEnd: ");
                    mAddToShoppingFloatingActionButton.show();
                    mNameItemTextView.setVisibility(View.VISIBLE);
                    mAboutTextView.setVisibility(View.VISIBLE);
                    mPriceItemTextView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onTransitionCancel(@NonNull Transition transition) {
                    Log.d(TAG, "onTransitionCancel: ");
                }

                @Override
                public void onTransitionPause(@NonNull Transition transition) {
                    Log.d(TAG, "onTransitionPause: ");
                }

                @Override
                public void onTransitionResume(@NonNull Transition transition) {
                    Log.d(TAG, "onTransitionResume: ");
                }
            });
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        View view =  inflater.inflate(R.layout.fragment_item_info, container, false);
        ButterKnife.bind(this, view);
        populateView();
        enableDrawer(false);


        return view;
    }

    private void populateView() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(getActivity()).load(mItem.getImageUrl()).into(mItemImageView);
        mNameItemTextView.setText(mItem.getNameFull());
        mPriceItemTextView.setText(Utils.addSymbolOfRuble(mItem.getCost()));
        mAdditionalInfoListView.setAdapter(new AdditionalInfoAdapter(mItem.getExtraInfo(), getActivity()));
        mAboutTextView.setText(mItem.getAbout());
        mCollapsingToolbarLayout.setTitleEnabled(true);
        mCollapsingToolbarLayout.setTitle(mItem.getNameFull());
        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        mAddToShoppingFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void goToShoppingCart() {
        ShoppingCartActivity.goTo(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == PickNumberOfItemsAllertDialog.PICK_NUMBER_ACTION) {
            mPresenter.AddToShoppingCart(mItem, data.getExtras().getInt(PickNumberOfItemsAllertDialog.EXTRA_NUMBER));
            Snackbar.make(mCollapsingToolbarLayout, R.string.added, Snackbar.LENGTH_LONG)
                    .setAction(R.string.toShoppingCart, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            goToShoppingCart();
                        }
                    }).show();
        }
    }


    @Override
    public void onClick(View view) {
        PickNumberOfItemsAllertDialog.showDialog(getActivity().getSupportFragmentManager(),
                ItemInfoFragment.this);
    }


    public static class AdditionalInfoAdapter extends BaseAdapter {

        private Map<Integer, Pair<String, String>> mItems;
        private LayoutInflater mInflater;
        private Context mContext;

        public AdditionalInfoAdapter(ExtraInfo extraInfo, Context context) {
            this.mItems = extraInfo.toMap(context);
            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int i) {
            return mItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Pair<String, String> info = mItems.get(position);
            ExtraInfoConverted extraInfoConverted = new ExtraInfoConverted(info.first, info.second, mContext);
            convertView = mInflater.inflate(R.layout.parameter_item_rom, parent, false);
            TextView parameterNameTextView = (TextView) convertView.findViewById(R.id.parameter_name_tv);
            TextView parameterInfoTextView = (TextView) convertView.findViewById(R.id.parameter_info_tv);
            ImageView paramterImageView = (ImageView) convertView.findViewById(R.id.parameter_icon_iv);

            parameterInfoTextView.setText(extraInfoConverted.getmParameterInfo());
            parameterNameTextView.setText(extraInfoConverted.getmParameterName());
            paramterImageView.setImageResource(extraInfoConverted.getmIconId());
            return convertView;
        }
    }

}
