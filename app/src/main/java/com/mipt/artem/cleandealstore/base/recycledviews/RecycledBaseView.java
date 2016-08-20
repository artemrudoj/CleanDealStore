package com.mipt.artem.cleandealstore.base.recycledviews;

import java.util.List;

/**
 * Created by artem on 27.05.16.
 */
public interface RecycledBaseView<T> {
    void showLoading();
    void stopLoading();
    void showEmpty();

    void showData(List<T> data);
}
