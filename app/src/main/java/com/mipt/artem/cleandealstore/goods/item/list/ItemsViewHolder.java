package com.mipt.artem.cleandealstore.goods.item.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;

/**
 * Created by artem on 20.08.16.
 */
public class ItemsViewHolder  extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name;
    public TextView price;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name_item_tv);
        price = (TextView) itemView.findViewById(R.id.price_item_tv);
        image = (ImageView) itemView.findViewById(R.id.image_item_iv);
    }
}