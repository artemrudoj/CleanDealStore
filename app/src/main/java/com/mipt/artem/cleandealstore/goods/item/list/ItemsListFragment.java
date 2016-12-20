package com.mipt.artem.cleandealstore.goods.item.list;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.goods.item.info.ItemInfoFragment;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.ui.DetailsTransition;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.transition.TransitionSet.ORDERING_TOGETHER;


public class ItemsListFragment extends RecyclerViewBaseFragment<Item> implements ItemsListView {

    private static final String EXTRA_SUBCATEGORY = "ItemsListFragment.EXTRA_SUBCATEGORY";

    @Inject
    ItemsListPresenter mPresenter;

    @Bind(R.id.items_rv)
    RecyclerView mItemsRecyclerView;

    private Category mCategory;
    private ItemsAdapter mAdapter;

    public ItemsListFragment() {
        // Required empty public constructor
    }


    public static ItemsListFragment newInstance(Category category) {
        ItemsListFragment fragment = new ItemsListFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_SUBCATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    private Category getSubCategotyFromArguments() {
        Bundle bundle = getArguments();
        Category category;
        if (bundle.containsKey(EXTRA_SUBCATEGORY)) {
            category = bundle.getParcelable(EXTRA_SUBCATEGORY);
        } else {
            throw new IllegalArgumentException("can not found category");
        }
        return category;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
        mCategory = getSubCategotyFromArguments();

    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbar(mCategory.getName());
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


        mPresenter.onCreateView(savedInstanceState, mCategory);
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
    public void showData(List<Item> data) {
        mAdapter = new ItemsAdapter(data, mPresenter);
        mItemsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void goToItemInfo(Item item, View image) {
        Fragment fragment = ItemInfoFragment.newInstance(item);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            fragment.setSharedElementEnterTransition(new DetailsTransition());
////            fragment.setEnterTransition(new Fade());
////            Transition explodeTransform = TransitionInflater.from(getActivity()).
////                    inflateTransition(android.R.transition.explode);
////            setExitTransition(explodeTransform);
////            setExitTransition(new Fade());
////            fragment.setSharedElementReturnTransition(new DetailsTransition());
////
////            Transition changeTransform = TransitionInflater.from(this).
////                    inflateTransition(R.transition.change_image_transform);
////            Transition explodeTransform = TransitionInflater.from(this).
////                    inflateTransition(android.R.transition.explode);
//
//            // Setup exit transition on first fragment
//            Transition changeTransform = TransitionInflater.from(getActivity()).
//                    inflateTransition(R.transition.change_image_transform);
//            Transition explodeTransform = TransitionInflater.from(getActivity()).
//                    inflateTransition(android.R.transition.fade);
//            setSharedElementReturnTransition(changeTransform);
////            setExitTransition(explodeTransform);
//
//            // Setup enter transition on second fragment
//            fragment.setSharedElementEnterTransition(changeTransform);
//            fragment.setEnterTransition(explodeTransform);
//
//            // Find the shared element (in Fragment A)
//
//            // Add second fragment by replacing first
//            FragmentTransaction ft = getFragmentManager().beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .addToBackStack("transaction")
//                    .addSharedElement(image, getString(R.string.transition_image_item_info));
//            // Apply the transaction
//            ft.commit();
//        } else {
////            getFragmentManager().beginTransaction()
////                    .addToBackStack(null)
////                    .addSharedElement(image, getString(R.string.transition_image_item_info))
////                    .replace(R.id.container, fragment)
////                    .commit();
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(image, getString(R.string.transition_image_item_info))
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();

    }


}
