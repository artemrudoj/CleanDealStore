package com.mipt.artem.cleandealstore.goods.item.info;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseFragment;

/**
 * Created by artem on 21.08.16.
 */
public class PickNumberOfItemsAllertDialog extends DialogFragment {
    public final static int PICK_NUMBER_ACTION = 32;
    public final static String EXTRA_NUMBER = "PickNumberOfItemsAllertDialog.EXTRA_NUMBER";
    EditText mEditText;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mEditText = new EditText(getActivity());
        mEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(mEditText);
        builder.setMessage(R.string.enter_count)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            int count = Integer.parseInt(mEditText.getText().toString());
                            sendResult(Activity.RESULT_OK, count);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getActivity(), R.string.incorrect_goods_count, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null);
        return builder.create();
    }

    static public void showDialog(FragmentManager fragmentManager, BaseFragment fragment) {
        PickNumberOfItemsAllertDialog dialog = new PickNumberOfItemsAllertDialog();
        dialog.setTargetFragment(fragment, PICK_NUMBER_ACTION);
        dialog.show(fragmentManager, null);
    }

    private void sendResult(int resultCode, int count) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NUMBER, count);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
