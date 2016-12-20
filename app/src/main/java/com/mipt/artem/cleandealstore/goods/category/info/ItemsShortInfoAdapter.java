package com.mipt.artem.cleandealstore.goods.category.info;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

import java.util.List;

/**
 * Created by artem on 10.12.16.
 */

public class ItemsShortInfoAdapter extends RecyclerView.Adapter<ItemsShortInfoAdapter.ViewHolder> {


    List<Item> items;


    public ItemsShortInfoAdapter(List<Item> list) {
        items = list;
    }


    @Override
    public ItemsShortInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemsShortInfoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsShortInfoAdapter.ViewHolder holder, int position) {
        holder.text.setText(items.get(position).getCost());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private Button button;
        private RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.name_item_tv);
            button = (Button) itemView.findViewById(R.id.show_detail_info_btn);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.info_container_rv);
        }
    }
}
