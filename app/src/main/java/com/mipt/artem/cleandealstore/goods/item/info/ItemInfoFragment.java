package com.mipt.artem.cleandealstore.goods.item.info;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.rest.responcedata.ExtraInfo;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.utils.AppBarStateChangeListener;
import com.squareup.picasso.Picasso;


import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.mipt.artem.cleandealstore.utils.AppBarStateChangeListener.State.COLLAPSED;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemInfoFragment extends BaseFragment implements ItemInfoView{
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

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.about_tv)
    TextView mAboutTextView;

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
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
        mItem = Item.getItemFromBundle(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_info, container, false);
        ButterKnife.bind(this, view);
        populateView();
        return view;
    }

    private void populateView() {
        Picasso.with(getActivity()).load(mItem.getImageUrl()).into(mItemImageView);
        mNameItemTextView.setText(mItem.getNameFull());
        mPriceItemTextView.setText(Utils.addSymbolOfRuble(mItem.getCost()));
        mAboutTextView.setText(mItem.getAbout());
        mAdditionalInfoListView.setAdapter(new AdditionalInfoAdapter(mItem.getExtraInfo(), getActivity()));
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        mToolbar.getMenu().findItem(R.id.trash_item).setVisible(false);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case COLLAPSED:
                        mToolbar.getMenu().findItem(R.id.trash_item).setVisible(true);
                        break;
                    case EXPANDED:
                        mToolbar.getMenu().findItem(R.id.trash_item).setVisible(false);
                        break;
                }
//                Log.d("STATE", state.name());
            }
        });

    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
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
