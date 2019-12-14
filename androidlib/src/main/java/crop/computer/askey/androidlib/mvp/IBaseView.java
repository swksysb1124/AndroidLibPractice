package crop.computer.askey.androidlib.mvp;

/**
 * Created by weikai on 2018/12/20.
 */

public interface IBaseView<T> {
    void setPresenter(T presenter);
    void showProgressBar(Object pbInfo);
    void updateProgress(Object progress);
    void hideProgressBar(Object pbInfo);
    void showMessage(Object msgInfo);
    void hideMessage(Object msgInfo);
    void goToPage(Object pageInfo);
    void runOnUiThread(Runnable task);
}
