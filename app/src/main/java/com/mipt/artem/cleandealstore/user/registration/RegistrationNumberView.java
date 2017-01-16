package com.mipt.artem.cleandealstore.user.registration;

import com.mipt.artem.cleandealstore.network.UserVO;

/**
 * Created by artem on 14.01.17.
 */

public interface RegistrationNumberView {
    void successGetCode();

    void successLoginByCode(UserVO userVO);

    void showLoading();

    void stopLoading();
}
