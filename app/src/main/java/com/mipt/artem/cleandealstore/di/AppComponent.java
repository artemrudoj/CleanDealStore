package com.mipt.artem.cleandealstore.di;


import com.mipt.artem.cleandealstore.base.BasePresenter;
import com.mipt.artem.cleandealstore.model.GoodsModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(BasePresenter basePresenter);

    void inject(GoodsModel model);
//
//    void inject(GoodsModel dataRepository);
//
//    void inject(BasePresenter basePresenter);
//
//    void inject(RepoListPresenter repoListPresenter);
//
//    void inject(RepoInfoPresenter repoInfoPresenter);
//
//    void inject(RepoInfoFragment repoInfoFragment);
}
