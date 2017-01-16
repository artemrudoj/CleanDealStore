package com.mipt.artem.cleandealstore.ui;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by artem on 26.02.16.
 */
public abstract class ValidationTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validate();
    }

    public abstract void validate();
}
