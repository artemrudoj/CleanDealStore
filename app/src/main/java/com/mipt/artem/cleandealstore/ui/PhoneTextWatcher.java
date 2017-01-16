package com.mipt.artem.cleandealstore.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 *
 * Created by vadim on 18/03/16.
 */

//// TODO: 21/03/16 refactor
public class PhoneTextWatcher implements TextWatcher {
    public static final String TAG = "PhoneTextWatcher";

    private String before;
    private String after;
    private int selection;
    private boolean disableChange = false;
    private EditText editText;
    private boolean needToDelete = false;


    public PhoneTextWatcher(EditText editText) {
        this.editText = editText;
        if (!editText.getText().toString().equals("")) {
            editText.setSelection(3); //// TODO: 21/03/16 calculate dynamically depend on country code
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (disableChange) {
            return;
        }
        Log.d(TAG, "beforeTextChanged: " + s);
        before = s.toString();
        selection = start;
        if (count > after) {
            boolean isDigit = Character.isDigit(s.charAt(start));
            needToDelete = isDigit && start > 2; //// TODO: 21/03/16 calculate dynamically depend on country code
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String s1 = s.toString();
        if (disableChange || s1.equals("") || s1.equals(after)) {
            return;
        }
        Log.d(TAG, "onTextChanged: " + s);
        StringBuilder stringBuilder = new StringBuilder(s1);
        //// FIXME: 25/03/16 CRUTCH !!!!!
        if (count > 15) { //for method setText
            after = s1;
            selection = calculateSelection(after);
            return;
        }
        if (count > before) { // adding new symbols
            if (!Character.isDigit(s.charAt(start))) {
                after = this.before;
                return;
            }
            int nextNum = stringBuilder.indexOf("_");
            if (nextNum < 0) {
                return;
            }
            stringBuilder.deleteCharAt(nextNum);
            stringBuilder.insert(nextNum, s.charAt(start));
            stringBuilder.deleteCharAt(start);
            after = stringBuilder.toString();
            after = after.substring(0, nextNum).replace(" ", "") + after.substring(nextNum);
            selection = calculateSelection(after);
        } else { //deleting symbols
            if (needToDelete) {
                stringBuilder.insert(start, "_");
                stringBuilder.insert(start, " ");
                after = stringBuilder.toString();
                needToDelete = false;
            } else {
                after = this.before;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            return;
        }
        if (disableChange) {
            disableChange = false;
            return;
        }
        Log.d(TAG, "afterTextChanged: " + s);
        if (after != null) {
            disableChange = true;
            s.clear();
            s.append(after);
            if (selection < editText.length()) {
                if (selection < 0) {
                    selection = editText.length();
                } else if (selection == 0) {
                    selection = calculateSelection(after);
                }
            } else {
                selection = editText.length();
            }
            editText.setSelection(selection);
        }
    }

    private int calculateSelection(String phoneMask) {
        int selection = phoneMask.indexOf("_");
        if (selection < 0) {
            selection = editText.length();
        }
        return selection;
    }
}
