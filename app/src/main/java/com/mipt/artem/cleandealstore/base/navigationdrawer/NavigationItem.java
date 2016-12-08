package com.mipt.artem.cleandealstore.base.navigationdrawer;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by artem on 22.05.16.
 */
public class NavigationItem implements TapHandler {


    private Drawable icon;
    private String text;
    private boolean loginRequired = false;
    private boolean shouldCloseDrawer = true;
    private TapHandler onTapHandler = null;
    private boolean validationNeeded;

    public NavigationItem(Drawable icon,  String text, boolean loginRequired,  boolean validationNeeded, TapHandler onTapHandler) {
        this.icon = icon;
        this.text = text;
        this.loginRequired = loginRequired;
        this.onTapHandler = onTapHandler;
        this.validationNeeded = validationNeeded;
    }

    public boolean isShouldCloseDrawer() {
        return shouldCloseDrawer;
    }


    public Drawable getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public NavigationItem(Context context, int icon, int text, boolean loginRequired, boolean validationNeeded, TapHandler onTapHandler) {
        this.icon = context.getResources().getDrawable(icon);
        this.text = context.getString(text);
        this.loginRequired = loginRequired;
        this.onTapHandler = onTapHandler;
        this.validationNeeded = validationNeeded;
    }

    @Override
    public void onTap() {
        if (onTapHandler != null){
            onTapHandler.onTap();
        }
    }

    public boolean isLoginRequired() {
        return loginRequired;
    }

    public boolean isValidationNeeded() {
        return validationNeeded;
    }

    public void setLoginRequired(boolean loginRequired) {
        this.loginRequired = loginRequired;
    }
}
