package com.mipt.artem.cleandealstore.base.navigationdrawer;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
    LayoutInflater mInflater;
    Context mContext;

    public NavigationDrawerListBaseAdapter(Context context, List<NavigationItem> objects) {
        mItems = objects;
        mContext = context.getApplicationContext();
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


        ((TextView) view.findViewById(R.id.record_text_tv)).setText(navigationItem.getText());
        Drawable drawable = navigationItem.getIcon();
        drawable.setColorFilter(new
                PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY));
        ((ImageView)view.findViewById(R.id.record_ic_iv)).setImageDrawable(drawable);

        return view;
    }
}
