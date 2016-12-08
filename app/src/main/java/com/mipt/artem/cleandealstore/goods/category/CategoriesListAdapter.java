package com.mipt.artem.cleandealstore.goods.category;

import com.mipt.artem.cleandealstore.base.BaseAdapter;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;

/**
 * Created by artem on 16.08.16.
 */
public class CategoriesListAdapter extends BaseAdapter<Category> {
    private CategoriesListPresenter mPresenter;


    public CategoriesListAdapter(List<Category> list, CategoriesListPresenter presenter) {
        super(list);
        mPresenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        final Category category = list.get(position);
        holder.text.setText(category.getName());
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickCategory(category));
    }
}
