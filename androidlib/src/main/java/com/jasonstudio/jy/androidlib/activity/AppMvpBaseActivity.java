package com.jasonstudio.jy.androidlib.activity;

import com.jasonstudio.jy.androidlib.mvp.IBasePresenter;

public abstract class AppMvpBaseActivity <T extends IBasePresenter> extends AppBaseActivity {

    public abstract T getPresenter();
}
