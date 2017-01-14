package com.mipt.artem.cleandealstore.ui;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mipt.artem.cleandealstore.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by artem on 13.01.17.
 */

public class SelectBasketTypeDialogFragment extends DialogFragment {


    public static final String ITEMS_COUNT = "SelectBasketTypeDialogFragment.ITEMS_COUNT";
    public static final String IS_IN_SUBSCRIPTION = "SelectBasketTypeDialogFragment.IS_IN_SUBSCRIPTION";

    private final int IN_SUBSCRIPTION = 1;
    private final int IN_ONE_TIME_DELIVERY = 0;



    @Bind(R.id.number_picker_with_arrows)
    NumberPickerWithArrows numberPickerWithArrows;
    @Bind(R.id.tabs)
    TabLayout mSelectBasketTypeTabLayout;

    @Bind(R.id.btn_cancel)
    Button mNegativeButton;
    @Bind(R.id.btn_ok)
    Button mPositiveButton;

    @Bind(R.id.additional_info_tv)
    TextView mAdditionalInfoTextView;
    @Bind(R.id.count_info_tv)
    TextView mCountInfoTextView;

    String[] mItemsForOneTimeDelivery;
    String[] mItemsForSubscription;

    public static final int MAX_ITEMS_FOR_ONE_TIME_DELIVERY = 99;
    public static final int MAX_ITEMS_FOR_SUBSCRIPTION = 99;

    static public void show(FragmentManager fragmentManager, Fragment fragment, int type) {
        SelectBasketTypeDialogFragment dialog = new SelectBasketTypeDialogFragment();
        dialog.setTargetFragment(fragment, type);
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialog.show(fragmentManager, "dialog");
    }


    private void generateValues() {
        mItemsForOneTimeDelivery = generateValues(MAX_ITEMS_FOR_ONE_TIME_DELIVERY,  getString(R.string.dim_items));
        mItemsForSubscription = generateValues(MAX_ITEMS_FOR_SUBSCRIPTION,  getString(R.string.dim_people));
    }

    String[] generateValues(int maxValues, String dim) {
        String [] arrayOfValues = new String[maxValues];
        for (int i = 0; i < maxValues; i++) {
            arrayOfValues[i] = Integer.toString(i + 1) + " " + dim;
        }
        return arrayOfValues;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.select_basket_type_dialog, container, false);
        ButterKnife.bind(this, v);
        generateValues();
        initTabLayout();
        initOKCancelButtons();
        return v;
    }

    private void initOKCancelButtons() {
        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResultToTarget();
                dismiss();
            }
        });
    }

    private void sendResultToTarget() {
        Intent intent = new Intent();
        intent.putExtra(SelectBasketTypeDialogFragment.ITEMS_COUNT, numberPickerWithArrows.getNumberPicker().getValue() + 1);
        intent.putExtra(SelectBasketTypeDialogFragment.IS_IN_SUBSCRIPTION, mSelectBasketTypeTabLayout.getSelectedTabPosition() == IN_SUBSCRIPTION);
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        }
    }


    private void initTabLayout() {
        mSelectBasketTypeTabLayout.addTab(mSelectBasketTypeTabLayout.newTab()
                .setText(R.string.to_one_time_delivery_full));
        mSelectBasketTypeTabLayout.addTab(mSelectBasketTypeTabLayout.newTab()
                .setText(getString(R.string.to_subscription)));
        mSelectBasketTypeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case IN_ONE_TIME_DELIVERY:
                        mCountInfoTextView.setText(R.string.count_info_for_one_time_delivery);
                        mAdditionalInfoTextView.setVisibility(View.INVISIBLE);
                        numberPickerWithArrows.getNumberPicker().setDisplayedValues(mItemsForOneTimeDelivery);
                        numberPickerWithArrows.getNumberPicker().setMaxValue(MAX_ITEMS_FOR_ONE_TIME_DELIVERY - 1);
                        break;
                    case IN_SUBSCRIPTION:
                        mCountInfoTextView.setText(R.string.count_info_for_subscription);
                        mAdditionalInfoTextView.setVisibility(View.VISIBLE);
                        mAdditionalInfoTextView.setText(R.string.addtitional_info_for_subscription);
                        numberPickerWithArrows.getNumberPicker().setDisplayedValues(mItemsForSubscription);
                        numberPickerWithArrows.getNumberPicker().setMaxValue(MAX_ITEMS_FOR_SUBSCRIPTION - 1);
                        break;

                    default:
                        throw new UnsupportedOperationException("unknown tab number: " + String.valueOf(tab.getPosition()));
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mSelectBasketTypeTabLayout.getTabAt(1).select();
    }

}
