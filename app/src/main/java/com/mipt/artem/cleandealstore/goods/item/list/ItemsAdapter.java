package com.mipt.artem.cleandealstore.goods.item.list;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.Utils;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by artem on 20.08.16.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    private List<Item> mItems;
    private ItemsListPresenter mPresenter;


    public ItemsAdapter(List<Item> list, ItemsListPresenter itemsListPresenter) {
        this.mItems = list;
        this.mPresenter = itemsListPresenter;
    }



    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.name.setText(item.getNameFull());
        holder.price.setText(Utils.addSymbolOfRuble(item.getCost()));
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickItem(item, holder.image));
        ViewCompat.setTransitionName(holder.image, String.valueOf(position) + "_image");
        Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
