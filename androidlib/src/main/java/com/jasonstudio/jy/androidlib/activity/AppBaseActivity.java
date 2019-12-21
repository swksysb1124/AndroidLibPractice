package com.jasonstudio.jy.androidlib.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;

public abstract class AppBaseActivity
        extends BaseActivity {

    protected abstract ProgressBar getProgressBar();

    protected void showProgressBar() {
        if(getProgressBar() != null) {
            getProgressBar().setVisibility(View.VISIBLE);
        }
    }

    protected void hideProgressBar() {
        if(getProgressBar() != null) {
            getProgressBar().setVisibility(View.INVISIBLE);
        }
    }

    public void replaceFragment(int contentId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(contentId, fragment);
        transaction.commit();
    }

    public void AddFragment(int contentId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(contentId, fragment);
        transaction.commit();
    }
}
