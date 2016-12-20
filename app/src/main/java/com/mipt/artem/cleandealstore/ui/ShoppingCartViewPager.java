package com.mipt.artem.cleandealstore.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by artem on 13.12.16.
 */

public class ShoppingCartViewPager extends ViewPager {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int ALL = 3;
    public static final int NONE = 4;

    public ShoppingCartViewPager(Context context) {
        super(context);
        init();
    }

    public ShoppingCartViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void allowScrollAccordingToPosition(int position) {
        if (position == 0) {
            setAllowedSwipeDirection(RIGHT);
        } else if (position == getAdapter().getCount() - 1) {
            setAllowedSwipeDirection(LEFT);
        } else {
            setAllowedSwipeDirection(ALL);
        }
    }

    private void init() {
        allowScrollAccordingToPosition(getCurrentItem());
        direction = RIGHT;
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                allowScrollAccordingToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private float initialXValue;
    private int direction;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.IsSwipeAllowed(event)) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.IsSwipeAllowed(event)) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    private boolean IsSwipeAllowed(MotionEvent event) {
        if(this.direction == ALL) return true;

        if(direction == NONE )//disable any swipe
            return false;

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            initialXValue = event.getX();
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            float diffX = event.getX() - initialXValue;
            if (diffX > 0 && direction == RIGHT ) {
                // swipe from left to right detected
                return false;
            }else if (diffX < 0 && direction == LEFT ) {
                // swipe from right to left detected
                return false;
            }
        }
        return true;
    }

    public void setAllowedSwipeDirection(int direction) {
        if ((direction != ALL) &&
            (direction != NONE) &&
            (direction != RIGHT) &&
            (direction != LEFT)) {
            throw new IllegalArgumentException("incorrect motion type " + direction);
        }
        this.direction = direction;
    }
}
