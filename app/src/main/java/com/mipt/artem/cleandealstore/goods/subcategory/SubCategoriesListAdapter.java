package com.mipt.artem.cleandealstore.goods.subcategory;

import com.mipt.artem.cleandealstore.base.BaseAdapter;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

import java.util.List;

/**
 * Created by artem on 20.08.16.
 */
public class SubCategoriesListAdapter extends BaseAdapter<Subcategory> {
    private SubCategoriesListPresenter mPresenter;


    public SubCategoriesListAdapter(List<Subcategory> list, SubCategoriesListPresenter presenter) {
        super(list);
        mPresenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        final Subcategory category = list.get(position);
        holder.text.setText(category.getName());
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickSubCategory(category));
    }
}