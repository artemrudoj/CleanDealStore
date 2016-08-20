package com.mipt.artem.cleandealstore.base.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.mipt.artem.cleandealstore.R;

import java.util.List;

/**
 * Created by artem on 22.05.16.
 */
public class NavigationDrawerListBaseAdapter extends BaseAdapter {

    List<NavigationItem> mItems;
    Context mContext;
    LayoutInflater mInflater;

    public NavigationDrawerListBaseAdapter(Context context, List<NavigationItem> objects) {
        mContext = context;
        mItems = objects;
        mInflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        NavigationItem navigationItem = mItems.get(position);
        view = mInflater.inflate(R.layout.simple_record_item, parent, false);


        ((TextView) view.findViewById(R.id.record_text_tv)).setText(mContext.getString(mItems.get(position).getText()));
        ((ImageView)view.findViewById(R.id.record_ic_iv)).setImageResource(mItems.get(position).getIcon());

        return view;
    }
}
