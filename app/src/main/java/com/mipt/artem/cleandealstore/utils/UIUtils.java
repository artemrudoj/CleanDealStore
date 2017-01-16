package com.mipt.artem.cleandealstore.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.mipt.artem.cleandealstore.R;

/**
 * Created by artem on 14.01.17.
 */

public class UIUtils {
    public static void disableEditText(Context context, EditText editText) {
        editText.setKeyListener(null);
        editText.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
        editText.setHintTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        editText.setBackgroundResource(R.drawable.dotted);
        editText.setFocusable(false);
    }
    public static boolean validateForPhoneNumber(EditText editText, TextInputLayout phoneTextInputLayout, int cannot_be_empty, Activity activity) {
        String number = UIUtils.getPhoneNumberFromFormattedNumber(editText.getText().toString());
        if (number.length() != 10) {
            phoneTextInputLayout.setError(activity.getString(cannot_be_empty));
            UIUtils.requestFocus(editText, activity);
            return false;
        } else {
            phoneTextInputLayout.setError(null);
            return true;
        }
    }

    public static String getPhoneNumberFromFormattedNumber(String number) {
        if (number == null || number.equals("")) {
            return "";
        }
        return number.replaceAll("\\D+","").substring(1);
    }

    public static void requestFocus(View view, Activity appCompatActivity) {
        if (view.requestFocus()) {
            appCompatActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static ProgressDialog createDownloadingProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.pleas_wait));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void hideProgressDialog(ProgressDialog progressDialog) {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
