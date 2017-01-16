package com.mipt.artem.cleandealstore.user.changeinfo;

/**
 * Created by artem on 16.01.17.
 */

public interface ChangeUserInfoView {
    void showLoading();
    void stopLoading();
    void successChanged();
}
