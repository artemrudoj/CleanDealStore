package com.mipt.artem.cleandealstore.goods.category;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public CategoriesListAdapter(List<CategoryWithDetailInfoHolder> list, CategoriesListPresenter presenter) {
        mPresenter = presenter;
        items = list;
    }

    @Override
    public CategoriesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CategoriesListAdapter.ViewHolder holder, int position) {
        final CategoryWithDetailInfoHolder categoryWithDetailInfoHolder = items.get(position);
        final Category category = categoryWithDetailInfoHolder.getCategory();
        holder.text.setText(category.getName());
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickCategory(category));
        holder.button.setOnClickListener(v ->
                mPresenter.clickShowCategoryDetail(categoryWithDetailInfoHolder, holder));
        if (categoryWithDetailInfoHolder.isDetailShown()) {
            RecyclerView currentRecyclerView = holder.recyclerView;
            currentRecyclerView.setVisibility(View.VISIBLE);
            bindToRecyclerView(currentRecyclerView, categoryWithDetailInfoHolder);
        } else {
            holder.recyclerView.setVisibility(View.GONE);
        }
    }

    private void bindToRecyclerView(RecyclerView recyclerView,
                                    CategoryWithDetailInfoHolder holder) {
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager manager;
        switch (holder.getCurrentDataType()) {
            case CategoryWithDetailInfoHolder.CATEGORIES_HOLDER:
                adapter = new CategoriesShortInfoAdapter(holder.getChildDirectories());
                manager = new LinearLayoutManager(recyclerView.getContext());
                break;
            case CategoryWithDetailInfoHolder.ITEMS_HOLDER:
                adapter = new ItemsShortInfoAdapter(holder.getItems());
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
        private Button button;
        public RecyclerView recyclerView;


        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
            button = (Button) itemView.findViewById(R.id.show_detail_info_btn);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.info_container_rv);
        }

        public void initRecyclerView(List<Category> categories) {
            recyclerView.setAdapter(new CategoriesShortInfoAdapter(categories));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        public void initRecyclerViewByItems(List<Item> items) {
            recyclerView.setAdapter(new ItemsShortInfoAdapter(items));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
