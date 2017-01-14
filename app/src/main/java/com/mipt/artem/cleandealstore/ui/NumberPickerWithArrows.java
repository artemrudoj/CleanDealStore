package com.mipt.artem.cleandealstore.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;




import com.mipt.artem.cleandealstore.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Vadim on 19/02/16.
 */
public class NumberPickerWithArrows extends LinearLayout {

    NumberPicker mNumberPicker;

    ImageView upArrowImageView;
    ImageView downArrowImageView;

    public NumberPickerWithArrows(Context context) {
        super(context);
        initialView(context);
    }

    public NumberPickerWithArrows(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialView(context);
    }

    public NumberPickerWithArrows(Context context,
                                  AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
        initialView(context);
    }

    int getValue() {
        return mNumberPicker.getValue();
    }


    public NumberPicker getNumberPicker() {
        return mNumberPicker;
    }

    private void initialView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.number_picker_with_arrows, this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mNumberPicker = (NumberPicker) findViewById(R.id.number_picker);
        upArrowImageView = (ImageView) findViewById(R.id.up_arrow_iv);
        upArrowImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValueByOne(mNumberPicker, false);
            }
        });
        downArrowImageView = (ImageView) findViewById(R.id.down_arrow_iv);
        downArrowImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValueByOne(mNumberPicker, true);
            }
        });
        mNumberPicker.setDescendantFocusability(android.widget.NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//        UIUtils.setDividerColor(mNumberPicker, getContext().getResources().getColor(R.color.yellow));
    }


    private void changeValueByOne(final android.widget.NumberPicker higherPicker, final boolean increment) {

        Method method;
        try {
            method = android.widget.NumberPicker.class.getDeclaredMethod("changeValueByOne", boolean.class);
            method.setAccessible(true);
            method.invoke(higherPicker, increment);

        } catch (final NoSuchMethodException | IllegalArgumentException |
                InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        mNumberPicker.setEnabled(enabled);
//        if(enabled) {
//            mNumberPicker.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
//            upArrowImageView.setImageResource(R.drawable.vector_arrow_up);
//            downArrowImageView.setImageResource(R.drawable.vector_arrow_down);
//            UIUtils.setDividerColor(mNumberPicker, ContextCompat.getColor(getContext(),
//                    com.groozinapplication.common.R.color.yellow), 255);
//        } else {
//            mNumberPicker.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_text));
//            upArrowImageView.setImageResource(R.drawable.vector_arrow_up_no_active);
//            downArrowImageView.setImageResource(R.drawable.vector_arrow_down_no_active);
//            UIUtils.setDividerColor(mNumberPicker, ContextCompat.getColor(getContext(),
//                    com.groozinapplication.common.R.color.yellow), 100);
//        }

    }





}
