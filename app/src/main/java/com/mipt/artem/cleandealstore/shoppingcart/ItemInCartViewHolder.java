package com.mipt.artem.cleandealstore.shoppingcart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.ui.ParameterWithCountLayout;

/**
 * Created by artem on 21.08.16.
 */
public class ItemInCartViewHolder  extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name;
    public TextView price;
    public ParameterWithCountLayout mPeopleParameterWithCountLayout;
    public ParameterWithCountLayout mItemsParameterWithCountLayout;
    public ParameterWithCountLayout mRestParameterWithCountLayout;

    public ItemInCartViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.goods_name_tv);
        price = (TextView) itemView.findViewById(R.id.price_tv);
        image = (ImageView) itemView.findViewById(R.id.goods_icon_iv);
        mPeopleParameterWithCountLayout = (ParameterWithCountLayout) itemView.findViewById(R.id.people_count_pwcl);
        mItemsParameterWithCountLayout = (ParameterWithCountLayout) itemView.findViewById(R.id.order_count_pwcl);
        mRestParameterWithCountLayout = (ParameterWithCountLayout) itemView.findViewById(R.id.rest_count_pwcl);
    }
}
