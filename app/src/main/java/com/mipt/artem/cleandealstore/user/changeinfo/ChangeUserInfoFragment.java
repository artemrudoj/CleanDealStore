package com.mipt.artem.cleandealstore.user.changeinfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mipt.artem.cleandealstore.base.BaseFragment;
import com.mipt.artem.cleandealstore.base.Presenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.di.view.DaggerViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewComponent;
import com.mipt.artem.cleandealstore.di.view.ViewDynamicModule;
import com.mipt.artem.cleandealstore.model.User;
import com.mipt.artem.cleandealstore.ui.ValidationTextWatcher;
import com.mipt.artem.cleandealstore.utils.UIUtils;

import javax.inject.Inject;

/**
 * Created by artem on 16.01.17.
 */

public class ChangeUserInfoFragment extends BaseFragment implements ChangeUserInfoView{



    @Bind(R.id.name_et)
    protected EditText name_et;
    @Bind(R.id.last_name_et)
    protected EditText last_name_et;
    @Bind(R.id.phone_number_et)
    protected EditText phone_number_et;
    @Bind(R.id.email_et)
    protected EditText email_et;
    @Bind(R.id.email_til)
    protected TextInputLayout emailTextInputLayout;

    @Inject
    ChangeUserInfoPresenter mPresenter;

    private ProgressDialog mProgressDialog;



    @OnClick(R.id.registrate_btn)
    public void requestConfirmationCodeForLogin(View view) {
        if (isValid()) {
            final String name =  name_et.getText().toString();
            final String lastName = last_name_et.getText().toString();
            final String eMail = email_et.getText().toString();
            mPresenter.changeUserInfo(name, lastName, eMail, null);
        }
    }

    public ChangeUserInfoFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.change_user_info, container, false);
        ButterKnife.bind(this, view);


        emailTextInputLayout = (TextInputLayout) view.findViewById(R.id.email_til);
        email_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validateEmail(false);
            }
        });
        email_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmail(true);
                }
            }
        });

        phone_number_et.setRawInputType(InputType.TYPE_CLASS_NUMBER);



        populateLayout(mPresenter.getCurrentUser());
        UIUtils.disableEditText(getActivity(), phone_number_et);

        return view;
    }


    private boolean validateEmail(boolean isShouldAlwaysSetText) {
        //need for client can set empty email
        if(email_et.getText().toString().equals("")) {
            emailTextInputLayout.setError(null);
            return true;
        } else {
            return UIUtils.validateEmailFormat(email_et, emailTextInputLayout,R.string.incorrect_email,
                    (AppCompatActivity)getActivity(), isShouldAlwaysSetText);
        }
    }


    protected void populateLayout(User user) {
        if (user.getFirstName() != null && !user.getFirstName().equals(User.DEFAULT_FIRST_NAME))
            name_et.setText(user.getFirstName());
        if (user.getLastName() != null) last_name_et.setText(user.getLastName());
        if (user.geteMail() != null) email_et.setText(user.geteMail());
        if (user.getPhone() != null && !user.getPhone().equals("")) {
            String phone = "+7" + user.getPhone();
            phone_number_et.setText(phone);
        }
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    private boolean isValid() {
        return validateEmail(true);
    }

    @Override
    public void successChanged() {
        getActivity().finish();
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
