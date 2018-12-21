package crop.computer.askey.androidlib.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import crop.computer.askey.androidlib.R;
import crop.computer.askey.androidlib.mvp.BasePresenter;

public abstract class AppMvpBaseActivity <T extends BasePresenter> extends AppBaseActivity {

    public abstract T getPresenter();
}
