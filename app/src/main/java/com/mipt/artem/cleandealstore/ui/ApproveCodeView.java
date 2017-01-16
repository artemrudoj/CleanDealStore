package com.mipt.artem.cleandealstore.ui;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mipt.artem.cleandealstore.R;


public class ApproveCodeView extends LinearLayout {

    private EditText confirmationCodeEditText;
    private TextInputLayout confirmationCodeTextInputLayout;

    public ApproveCodeView(Context context) {
        super(context);
        init(context);
    }

    public ApproveCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ApproveCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.approve_code, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        confirmationCodeEditText = (EditText) findViewById(R.id.confirm_code_et);
        confirmationCodeTextInputLayout = (TextInputLayout) findViewById(R.id.approve_code_til);
    }

    public TextInputLayout getConfirmationCodeTextInputLayout() {
        return confirmationCodeTextInputLayout;
    }

    public String getText() {
        return confirmationCodeEditText.getText().toString();
    }
}

