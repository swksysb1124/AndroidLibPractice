package com.jasonstudio.jy.androidlibpractice.view;

import com.jasonstudio.jy.androidlib.mvp.IBaseView;
import com.jasonstudio.jy.androidlibpractice.presenter.MainPresenterI;

/**
 * Created by weikai on 2018/12/21.
 */

public interface MainViewI
        extends IBaseView<MainPresenterI> {
    void showGetResult(String result);
}
