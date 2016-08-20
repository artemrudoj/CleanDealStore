package com.mipt.artem.cleandealstore.base.navigationdrawer;

/**
 * Created by artem on 22.05.16.
 */
public class NavigationItem implements TapHandler {


    int icon;
    int text;
    boolean loginRequired = false;
    boolean shouldCloseDrawer = true;
    TapHandler onTapHandler = null;
    boolean validationNeeded;

    public boolean isShouldCloseDrawer() {
        return shouldCloseDrawer;
    }





    public int getText() {
        return text;
    }
    public int getIcon() { return icon; }

    public void setText(int text) {
        this.text = text;
    }

    public NavigationItem(int icon, int text) {
        this(icon, text, false);
    }

    public NavigationItem(int icon, int text, boolean isCustom) {
        this.icon = icon;
        this.text = text;
    }

    public NavigationItem(int text, TapHandler onTapHandler) {
        this.text = text;
        this.onTapHandler = onTapHandler;
    }

    public NavigationItem(int icon, int text, boolean loginRequired,  boolean validationNeeded, TapHandler onTapHandler) {
        this.icon = icon;
        this.text = text;
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
