package com.mipt.artem.cleandealstore.di.user;

import com.mipt.artem.cleandealstore.model.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by artem on 20.08.16.
 */
@Module
public class UserModule {

    private User mUser;

    public UserModule(User user) {
        this.mUser = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return mUser;
    }

}
