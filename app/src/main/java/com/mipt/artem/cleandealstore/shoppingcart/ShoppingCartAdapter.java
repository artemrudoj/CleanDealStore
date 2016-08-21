package com.mipt.artem.cleandealstore.shoppingcart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.Utils;
import com.mipt.artem.cleandealstore.goods.item.list.ItemsViewHolder;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public class ShoppingCartAdapter  extends RecyclerView.Adapter<ItemInCartViewHolder> {

    private List<ItemInCart> mItems;
    private ShoppingCartPresenter mPresenter;


    public ShoppingCartAdapter(List<ItemInCart> list, ShoppingCartPresenter itemsListPresenter) {
        this.mItems = list;
        this.mPresenter = itemsListPresenter;
    }


    @Override
    public ItemInCartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopping_cart_row, viewGroup, false);
        return new ItemInCartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemInCartViewHolder holder, int position) {
        final ItemInCart item = mItems.get(position);
        holder.name.setText(item.getNameFull());
        holder.count.setText(Integer.toString(item.getCount()));
        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.decreaseCount(item);
                notifyDataSetChanged();
            }
        });
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.increaseCount(item);
                notifyDataSetChanged();
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.delete(item);
                notifyDataSetChanged();
            }
        });
        holder.price.setText(Utils.addSymbolOfRuble(item.getCost()));
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickItem(item));
        Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
