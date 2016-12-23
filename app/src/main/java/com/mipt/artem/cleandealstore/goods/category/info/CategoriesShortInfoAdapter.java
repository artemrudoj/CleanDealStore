package com.mipt.artem.cleandealstore.goods.category.info;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListPresenter;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;


/**
 * Created by artem on 10.12.16.
 */

public class CategoriesShortInfoAdapter extends RecyclerView.Adapter<CategoriesShortInfoAdapter.ViewHolder> {

    List<Category> items;
    private CategoriesListPresenter mPresenter;

    public CategoriesShortInfoAdapter(List<Category> list, CategoriesListPresenter presenter) {
        items = list;
        mPresenter = presenter;
    }


    @Override
    public CategoriesShortInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_detail_in_rv_row, parent, false);
        return new CategoriesShortInfoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriesShortInfoAdapter.ViewHolder holder, int position) {
        Category category = items.get(position);
        holder.text.setText(category.getName());
        holder.itemView.setOnClickListener(v -> mPresenter.clickCategory(category));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
