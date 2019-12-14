package crop.computer.askey.androidlib.mvp;

public interface BaseModel {
    void registerCallback(Callback callback);
    void unregister(Callback callback);

    interface Callback {
        void onSuccess(Object data);
        void onFail(String errorType, String errorMessage);
    }
}
