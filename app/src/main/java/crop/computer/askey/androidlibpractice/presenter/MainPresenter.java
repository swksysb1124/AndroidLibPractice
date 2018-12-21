package crop.computer.askey.androidlibpractice.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import crop.computer.askey.androidlib.http.remoteservice.ServiceDataReceiver;
import crop.computer.askey.androidlib.mvp.BasePresenter;
import crop.computer.askey.androidlibpractice.controller.MainActivity;
import crop.computer.askey.androidlibpractice.service.RemoteServiceImp;
import crop.computer.askey.androidlibpractice.view.MainView;
import crop.computer.askey.androidlibpractice.view.SubView;

/**
 * Created by weikai on 2018/12/21.
 */

public class MainPresenter
        implements BasePresenter {

    private MainActivity mActivity;

    private RemoteServiceImp mRemoteServiceImp;

    private MainView mMainView;

    private SubView mSubView;

    private Context mContext;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MainPresenter(Context context) {
        mContext = context;
        mActivity = (MainActivity) context;
    }


    public void setMainView(MainView mainView) {
        mMainView = mainView;
    }

    public void setSubView(SubView subView) {
        mSubView = subView;
    }

    @Override
    public void start() {
        mRemoteServiceImp = RemoteServiceImp.getInstance(mContext.getApplicationContext());
        mRemoteServiceImp.registerDataReceiver(new ServiceDataReceiver() {
            @Override
            public void onSuccess(final String key, final String content) {
                if(mHandler != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateDataReceivedToUi(key, content);
                        }
                    });
                }
            }

            @Override
            public void onFail(final String key, final int errorType, final String errorMessage) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateErrorReceivedToUi(key, errorType, errorMessage);
                    }
                });
            }
        });
    }

    private void updateDataReceivedToUi(String key, String content) {
        switch (key) {
            case "testGET":
                if(mMainView!=null) {
                    mMainView.showGetResult(content);
                }
                break;
            case "testPOST":
                if(mSubView!=null) {
                    mSubView.showPostResult(content);
                }
                break;
        }
    }

    public void testGetAtMain() {
        if(mRemoteServiceImp != null) {
            mRemoteServiceImp.testGET("k1", "k2");
        }
    }

    public void tesPostAtSub() {
        if(mRemoteServiceImp != null) {
            mRemoteServiceImp.testPOST("{\"name\":\"jason\"}");
        }
    }

    private void updateErrorReceivedToUi(String key, int errorType, String errorMessage) {
        switch (key) {
            case "testGET":
                if(mMainView != null) {
                    mMainView.showGetResult(errorMessage);
                }
                break;
            case "testPOST":
                if(mSubView != null) {
                    mSubView.showPostResult(errorMessage);
                }
                break;
        }
    }

    public void toMainView() {
        mActivity.toMainView();
    }

    public void toSubView() {
        mActivity.toSubView();
    }

    @Override
    public void finish() {
        mRemoteServiceImp.unregisterDataReceiver();
        mRemoteServiceImp = null;
    }
}
