package com.mipt.artem.cleandealstore.shoppingcart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;

/**
 * Created by artem on 21.08.16.
 */
public class ItemInCartViewHolder  extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name;
    public TextView price;
    public TextView count;
    public ImageButton increaseButton;
    public ImageButton decreaseButton;
    public ImageButton deleteButton;

    public ItemInCartViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.goods_name_tv);
        price = (TextView) itemView.findViewById(R.id.price_tv);
        image = (ImageView) itemView.findViewById(R.id.goods_icon_iv);
        count = (TextView) itemView.findViewById(R.id.count_tv);
        increaseButton = (ImageButton) itemView.findViewById(R.id.add_item_ib);
        decreaseButton = (ImageButton) itemView.findViewById(R.id.delcrease_item_ib);
        deleteButton = (ImageButton) itemView.findViewById(R.id.del_item_ib);
    }
}
