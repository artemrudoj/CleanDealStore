package com.mipt.artem.cleandealstore.network;

import com.google.gson.annotations.SerializedName;
import com.mipt.artem.cleandealstore.model.User;

/**
 * Created by artem on 16.01.17.
 */

public class UserVO {

    @SerializedName("is_new_user")
    boolean isNewUser;
    @SerializedName("user")
    User mUser;


    public boolean isNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }
}
