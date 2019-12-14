package crop.computer.askey.androidlibpractice.view;

import crop.computer.askey.androidlib.mvp.IBaseView;
import crop.computer.askey.androidlibpractice.presenter.MainPresenterI;

/**
 * Created by weikai on 2018/12/21.
 */

public interface SubViewI
        extends IBaseView<MainPresenterI> {
    void showPostResult(String result);

}
