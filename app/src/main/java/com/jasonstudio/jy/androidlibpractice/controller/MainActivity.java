package com.jasonstudio.jy.androidlibpractice.controller;

import android.os.Bundle;
import android.widget.ProgressBar;


import com.jasonstudio.jy.androidlib.activity.AppMvpBaseActivity;
import com.jasonstudio.jy.androidlibpractice.R;
import com.jasonstudio.jy.androidlibpractice.presenter.MainPresenterI;
import com.jasonstudio.jy.androidlibpractice.view.MainFragment;
import com.jasonstudio.jy.androidlibpractice.view.SubFragment;


public class MainActivity
        extends AppMvpBaseActivity<MainPresenterI> {

    private MainPresenterI mPresenter;

    private MainFragment mainFragment;

    private SubFragment subFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenterI(this);
        mPresenter.start();

        toMainView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.finish();
        }
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    public void toMainView() {
        if(mainFragment == null) {
            mainFragment = new MainFragment();
        }
        replaceFragment(R.id.fragment_container, mainFragment);
    }

    public void toSubView() {
        if(subFragment == null) {
            subFragment = new SubFragment();
        }
        replaceFragment(R.id.fragment_container, subFragment);
    }


    @Override
    public MainPresenterI getPresenter() {
        return mPresenter;
    }
}
