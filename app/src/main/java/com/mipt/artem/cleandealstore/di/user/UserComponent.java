package com.mipt.artem.cleandealstore.di.user;

import dagger.Subcomponent;

/**
 * Created by artem on 20.08.16.
 */
@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {
}
