package com.jasonstudio.jy.androidlib.mvp;

public interface IBaseModel {
    void registerCallback(Callback callback);
    void unregister(Callback callback);

    interface Callback {
        void onSuccess(Object data);
        void onFail(String errorType, String errorMessage);
    }
}
