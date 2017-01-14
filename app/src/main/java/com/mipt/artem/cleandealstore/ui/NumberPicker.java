package com.mipt.artem.cleandealstore.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;


public class NumberPicker extends android.widget.NumberPicker {

    EditText textEditText;

    public NumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(@NonNull View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(@NonNull View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(@NonNull View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }


    public void setTextColor(int color) {
        textEditText.setTextColor(color);
    }

    private void updateView(View view) {
        if(view instanceof EditText){
            textEditText = (EditText) view;
            textEditText.setTextSize(20);
            textEditText.setTextColor(Color.parseColor("#406379"));
        }
    }


}