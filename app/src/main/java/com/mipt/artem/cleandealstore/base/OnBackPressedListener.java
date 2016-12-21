package com.mipt.artem.cleandealstore.base;

/**
 * Created by artem on 21.12.16.
 */

public interface OnBackPressedListener {
    interface handler {
        //true if handled by listener
        boolean handleBackPressed();
    }
    interface controller {
        void addOnBackPressedListener(OnBackPressedListener.handler listener);
    }
}
