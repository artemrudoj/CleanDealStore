package com.mipt.artem.cleandealstore.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;



/**
 * Created by artem on 12.01.17.
 */

public class ParameterWithCountLayout extends LinearLayout {
    TextView mParameterNameTextView;
    TextView mParameterDimensionTextView;
    TextView mCountTextView;
    ImageButton mAddButton;
    ImageButton mSubButton;
    public ParameterWithCountLayout(Context context) {
        super(context);
        initView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mParameterNameTextView = (TextView) findViewById(R.id.parameter_name_tv);
        mParameterDimensionTextView = (TextView) findViewById(R.id.parameter_dim_tv);
        mCountTextView = (TextView) findViewById(R.id.count_tv);
        mAddButton = (ImageButton) findViewById(R.id.add_item_ib);
        mSubButton = (ImageButton) findViewById(R.id.sub_item_ib);
    }

    public ParameterWithCountLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.parameter_with_count_layout, this);
    }


    public void setOnAddButtonListener(OnClickListener l) {
        mAddButton.setOnClickListener(l);
    }

    public void setOnSubButtonListener(OnClickListener l) {
        mSubButton.setOnClickListener(l);
    }

    public void setCount(int count) {
        mCountTextView.setText(Integer.toString(count));
    }

    public void setDimension(String  dimension) {
        mParameterDimensionTextView.setText(dimension);
    }

    public void setParameterName(String name) {
        mParameterNameTextView.setText(name);
    }




}
