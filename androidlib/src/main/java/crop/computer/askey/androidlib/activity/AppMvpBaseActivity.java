package crop.computer.askey.androidlib.activity;

import crop.computer.askey.androidlib.mvp.IBasePresenter;

public abstract class AppMvpBaseActivity <T extends IBasePresenter> extends AppBaseActivity {

    public abstract T getPresenter();
}
