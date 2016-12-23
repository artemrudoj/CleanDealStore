package com.mipt.artem.cleandealstore.goods.category.info;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.Utils;
import com.mipt.artem.cleandealstore.goods.category.CategoriesListPresenter;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by artem on 10.12.16.
 */

public class ItemsShortInfoAdapter extends RecyclerView.Adapter<ItemsShortInfoAdapter.ViewHolder> {


    List<Item> items;
    private CategoriesListPresenter mPresenter;

    public ItemsShortInfoAdapter(List<Item> list, CategoriesListPresenter presenter) {
        mPresenter = presenter;
        items = list;
    }


    @Override
    public ItemsShortInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_preview, parent, false);
        return new ItemsShortInfoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsShortInfoAdapter.ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.name.setText(item.getNameFull());
        holder.price.setText(Utils.addSymbolOfRuble(item.getCost()));
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickItemShortInfo(item, holder.image));
        ViewCompat.setTransitionName(holder.image, String.valueOf(position) + "_image");
        Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_item_tv);
            price = (TextView) itemView.findViewById(R.id.price_item_tv);
            image = (ImageView) itemView.findViewById(R.id.image_item_iv);
        }
    }
}
