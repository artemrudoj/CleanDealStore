package com.mipt.artem.cleandealstore.registration;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.network.UserVO;
import com.mipt.artem.cleandealstore.ui.ApproveCodeView;
import com.mipt.artem.cleandealstore.ui.PhoneTextWatcher;
import com.mipt.artem.cleandealstore.utils.UIUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class RegistrationNumberFragment extends BaseFragment implements View.OnClickListener, RegistrationNumberView {

    private final static String STATE = "STATE";
    private final static String PHONE_NUMBER = "RegistrationNumberFragment.PHONE_NUMBER";
    private static final int ENTERING_PHONE_NUMBER = 0;
    private static final int ENTERING_APPROVING_CODE = 1;

    private int status;

    @Bind(R.id.agree_cb)
    CheckBox checkBox;

    @Bind(R.id.approve_btn)
    Button approveButton;

    @Bind(R.id.approve_code_container)
    ApproveCodeView approveCodeView;

    @Bind(R.id.phone_number_et)
    EditText phoneNumberET;

    @Bind(R.id.phone_number_container)
    TextInputLayout phoneEditTextTextInputLayout;

    @Bind(R.id.oferta_tv)
    TextView ofertaTextView;

    @Inject
    RegistrationNumberPresenter mPresenter;

    @OnClick(R.id.sendConfirmationOneMoreTime)
    public void requestConfirmationCodeForLogin(View view) {
        requestCode();
    }

    private ProgressDialog mProgressDialog;

    private void requestCode() {
        if (validatePhone()) {
            String phone = UIUtils.getPhoneNumberFromFormattedNumber(phoneNumberET.getText().toString());
            mPresenter.getConfirmationCode(phone);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewDynamicModule(new ViewDynamicModule(this))
                .build();
        viewComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_registration_number, container, false);
        ButterKnife.bind(this, view);




        phoneNumberET.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        phoneNumberET.addTextChangedListener(new PhoneTextWatcher(phoneNumberET) {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                validatePhone();
            }
        });


        approveButton.setOnClickListener(this);
        if(savedInstanceState != null) {
            status = savedInstanceState.getInt(STATE);
        } else {
            status = ENTERING_PHONE_NUMBER;
        }
        if (status == ENTERING_PHONE_NUMBER)
            approveCodeView.setVisibility(View.INVISIBLE);
        if (status == ENTERING_APPROVING_CODE)
            UIUtils.disableEditText(getActivity(), phoneNumberET);
            approveButton.setText(R.string.approve);

        return  view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, status);
    }

    public void onClick(View view) {
        if (checkBox.isChecked()) {
            switch (status) {
                case ENTERING_PHONE_NUMBER:
                    requestCode();
                    break;

                case ENTERING_APPROVING_CODE:
                    if (isApproveCodeCorrect()) {
                        mPresenter.loginByConfirmationCode(UIUtils.getPhoneNumberFromFormattedNumber(phoneNumberET.getText().toString()),
                                approveCodeView.getText());
                    }
                    break;
            }
        } else {
            Toast.makeText(getActivity(), R.string.agree_with_rules, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validatePhone() {
        Activity activity = getActivity();
        if(activity != null)
            return UIUtils.validateForPhoneNumber(phoneNumberET,phoneEditTextTextInputLayout,
                    R.id.incorrect_phone_number, (AppCompatActivity) activity);
        return false;
    }


    private boolean isApproveCodeCorrect() {
        if (approveCodeView.getText().equals("")) {
            approveCodeView.getConfirmationCodeTextInputLayout().setError(getString(R.string.inccorrect_approve_code));
            return false;
        } else {
            approveCodeView.getConfirmationCodeTextInputLayout().setError(null);
            return true;
        }
    }

    public RegistrationNumberFragment() {
        // Required empty public constructor
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void successGetCode() {
        UIUtils.disableEditText(getActivity(), phoneNumberET);
        approveButton.setText(getActivity().getString(R.string.approve));
        approveCodeView.setVisibility(View.VISIBLE);
        status = ENTERING_APPROVING_CODE;
    }

    @Override
    public void successLoginByCode(UserVO userVO) {

    }

    @Override
    public void showLoading() {
        mProgressDialog = UIUtils.createDownloadingProgressDialog(getActivity());
        mProgressDialog.show();
    }

    @Override
    public void stopLoading() {
        UIUtils.hideProgressDialog(mProgressDialog);
    }
}
