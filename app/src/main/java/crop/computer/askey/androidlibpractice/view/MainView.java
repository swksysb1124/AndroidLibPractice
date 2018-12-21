package crop.computer.askey.androidlibpractice.view;

import crop.computer.askey.androidlib.mvp.BaseView;
import crop.computer.askey.androidlibpractice.presenter.MainPresenter;

/**
 * Created by weikai on 2018/12/21.
 */

public interface MainView
        extends BaseView<MainPresenter> {
    void showGetResult(String result);
}
