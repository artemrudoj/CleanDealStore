package com.mipt.artem.cleandealstore.shoppingcart;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.Utils;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;
import com.mipt.artem.cleandealstore.ui.ParameterWithCountLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by artem on 21.08.16.
 */
public class ShoppingCartAdapter  extends RecyclerView.Adapter<ItemInCartViewHolder> {


    private static final int CHANGE_BASKET_ID = 0;
    private static final int REMOVE_ID = 1;

    private List<ItemInCart> mItems;
    private ShoppingCartBasePresenter mPresenter;
    private List<ItemInCart> items;


    public ShoppingCartAdapter(List<ItemInCart> list, ShoppingCartBasePresenter itemsListPresenter) {
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
        initParameterWithCountLayouts(item, holder);

        holder.price.setText(Utils.generatePriceString(Double.parseDouble(item.getCost()), item.getCount()));
        holder.itemView.setOnClickListener(v ->
                mPresenter.clickItem(item));
        Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.image);
        holder.mShowMenuButton.setOnClickListener(v -> showMenu(v, item));
    }

    private void showMenu(View v, final ItemInCart item) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        if (item.isInShoppingCart()) {
            popupMenu.getMenu().add(Menu.NONE, CHANGE_BASKET_ID, Menu.NONE, R.string.to_one_time_delivery_full);
            popupMenu.getMenu().add(Menu.NONE, REMOVE_ID, Menu.NONE, R.string.unsubscribe);
        } else {
            popupMenu.getMenu().add(Menu.NONE, CHANGE_BASKET_ID, Menu.NONE, R.string.to_subscription);
            popupMenu.getMenu().add(Menu.NONE, REMOVE_ID, Menu.NONE, R.string.delete);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem it) {
                switch (it.getItemId()) {
                    case CHANGE_BASKET_ID:
                        mPresenter.moveItemToAnotherBasket(item);
                        break;
                    case REMOVE_ID:
                        mPresenter.removeItem(item);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void initParameterWithCountLayouts(final ItemInCart item, ItemInCartViewHolder holder) {

        initParameterButton(holder.mItemsParameterWithCountLayout, item.getCount(), holder.itemView.getContext().getString(R.string.order_with_dotes),
                holder.itemView.getContext().getString(R.string.dim_items),new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.increaseCount(item);
                        notifyDataSetChanged();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.decreaseCount(item);
                        notifyDataSetChanged();
                    }
                }  );

        if (item.isInShoppingCart()) {
            holder.mRestParameterWithCountLayout.setVisibility(View.VISIBLE);
            initParameterButton(holder.mRestParameterWithCountLayout, item.getCount(), holder.itemView.getContext().getString(R.string.rest_with_dotes),
                    holder.itemView.getContext().getString(R.string.dim_items), null, null);
        } else {
            holder.mRestParameterWithCountLayout.setVisibility(View.GONE);
        }

        if (item.isInShoppingCart()) {
            holder.mPeopleParameterWithCountLayout.setVisibility(View.VISIBLE);
            initParameterButton(holder.mPeopleParameterWithCountLayout, item.getPeopleCount(), holder.itemView.getContext().getString(R.string.use_with_dotes),
                    holder.itemView.getContext().getString(R.string.dim_people), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.increasePeopleCount(item);
                            notifyDataSetChanged();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.decreasePeopleCount(item);
                            notifyDataSetChanged();
                        }
                    });
        } else {
            holder.mPeopleParameterWithCountLayout.setVisibility(View.GONE);
        }
    }

    private void initParameterButton(ParameterWithCountLayout layout, int count, String name,
                                     String dim, View.OnClickListener addListener, View.OnClickListener subListener) {
        layout.setCount(count);
        layout.setDimension(dim);
        layout.setParameterName(name);
        layout.setOnSubButtonListener(subListener);
        layout.setOnAddButtonListener(addListener);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public ItemInCart getItemById(int itemId) {
        if (mItems == null || mItems.size() == 0) {
            return null;
        } else {
            return mItems.get(itemId);
        }
    }

    public List<ItemInCart> getItems() {
        return items;
    }

    public void setItems(List<ItemInCart> items) {
        this.items = items;
    }
}
