package com.mipt.artem.cleandealstore.goods.category;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;

import com.mipt.artem.cleandealstore.goods.category.info.CategoriesShortInfoAdapter;
import com.mipt.artem.cleandealstore.goods.category.info.CategoryWithDetailInfoHolder;
import com.mipt.artem.cleandealstore.goods.category.info.ItemsShortInfoAdapter;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.List;

/**
 * Created by artem on 16.08.16.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>{
    private CategoriesListPresenter mPresenter;
    protected List<CategoryWithDetailInfoHolder> items;
    private static final int DURATION = 200;

    public CategoriesListAdapter(List<CategoryWithDetailInfoHolder> list, CategoriesListPresenter presenter) {
        mPresenter = presenter;
        items = list;
    }

    @Override
    public CategoriesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item_layout, parent, false);
        return new ViewHolder(v, mPresenter);
    }

    @Override
    public void onBindViewHolder(final CategoriesListAdapter.ViewHolder holder, int position) {
        final CategoryWithDetailInfoHolder categoryWithDetailInfoHolder = items.get(position);
        final Category category = categoryWithDetailInfoHolder.getCategory();
        holder.text.setText(category.getName());
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickCategory(category));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.button.startAnimation(createAnimationObject(categoryWithDetailInfoHolder.isDetailShown(), DURATION));
                mPresenter.clickShowCategoryDetail(categoryWithDetailInfoHolder, holder);
            }
        });
        if (categoryWithDetailInfoHolder.isDetailShown()) {
            RecyclerView currentRecyclerView = holder.recyclerView;
            currentRecyclerView.setVisibility(View.VISIBLE);
            bindToRecyclerView(currentRecyclerView, categoryWithDetailInfoHolder);
        } else {
            holder.recyclerView.setVisibility(View.GONE);
        }
    }

    private Animation createAnimationObject(boolean isOpened, int duration) {
        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);
        float startPosition;
        float endPosition;
        if (isOpened) {
            startPosition = 90.0f;
            endPosition = 0.0f;
        } else {
            startPosition = 0.0f;
            endPosition = 90.0f;
        }
        final RotateAnimation animRotate = new RotateAnimation(startPosition, endPosition ,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animRotate.setDuration(duration);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);
        return animRotate;
    }

    private void bindToRecyclerView(RecyclerView recyclerView,
                                    CategoryWithDetailInfoHolder holder) {
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager manager;
        switch (holder.getCurrentDataType()) {
            case CategoryWithDetailInfoHolder.CATEGORIES_HOLDER:
                adapter = new CategoriesShortInfoAdapter(holder.getChildDirectories(), mPresenter);
                manager = new LinearLayoutManager(recyclerView.getContext());
                break;
            case CategoryWithDetailInfoHolder.ITEMS_HOLDER:
                adapter = new ItemsShortInfoAdapter(holder.getItems(), mPresenter);
                manager = new LinearLayoutManager(recyclerView.getContext(),
                        LinearLayoutManager.HORIZONTAL, false);
                break;
            default:
                throw new IllegalStateException("cannot be both visible and no items on holder");
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageButton button;
        public RecyclerView recyclerView;
        private CategoriesListPresenter mPresenter;


        public ViewHolder(View itemView, CategoriesListPresenter presenter) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
            button = (ImageButton) itemView.findViewById(R.id.show_detail_info_btn);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.info_container_rv);
            mPresenter = presenter;
        }

        public void initRecyclerView(List<Category> categories) {
            recyclerView.setAdapter(new CategoriesShortInfoAdapter(categories, mPresenter));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        public void initRecyclerViewByItems(List<Item> items) {
            recyclerView.setAdapter(new ItemsShortInfoAdapter(items, mPresenter));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
