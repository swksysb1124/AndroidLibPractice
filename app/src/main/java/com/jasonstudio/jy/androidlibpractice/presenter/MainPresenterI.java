package com.jasonstudio.jy.androidlibpractice.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import com.jasonstudio.jy.androidlib.http.remoteservice.ServiceDataReceiver;
import com.jasonstudio.jy.androidlib.mvp.IBasePresenter;
import com.jasonstudio.jy.androidlibpractice.controller.MainActivity;
import com.jasonstudio.jy.androidlibpractice.service.HttpBinApiService;
import com.jasonstudio.jy.androidlibpractice.view.MainViewI;
import com.jasonstudio.jy.androidlibpractice.view.SubViewI;

/**
 * Created by weikai on 2018/12/21.
 */

public class MainPresenterI
        implements IBasePresenter {

    private MainActivity mActivity;

    private HttpBinApiService mRemoteServiceImp;

    private MainViewI mMainView;

    private SubViewI mSubView;

    private Context mContext;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MainPresenterI(Context context) {
        mContext = context;
        mActivity = (MainActivity) context;
    }


    public void setMainView(MainViewI mainView) {
        mMainView = mainView;
    }

    public void setSubView(SubViewI subView) {
        mSubView = subView;
    }

    @Override
    public void start() {
        mRemoteServiceImp = HttpBinApiService.getInstance(mContext.getApplicationContext());
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

            @Override
            public void onDisconnected(String key, String errorMessage) {

            }

            @Override
            public void onTimeout(String key, String errorMessage) {

            }

            @Override
            public void onUnknownHost(String key, String errorMessage) {

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
