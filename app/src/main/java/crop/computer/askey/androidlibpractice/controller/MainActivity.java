package crop.computer.askey.androidlibpractice.controller;

import android.os.Bundle;
import android.widget.ProgressBar;


import crop.computer.askey.androidlib.activity.AppMvpBaseActivity;
import crop.computer.askey.androidlibpractice.R;
import crop.computer.askey.androidlibpractice.presenter.MainPresenterI;
import crop.computer.askey.androidlibpractice.view.MainFragment;
import crop.computer.askey.androidlibpractice.view.SubFragment;


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
