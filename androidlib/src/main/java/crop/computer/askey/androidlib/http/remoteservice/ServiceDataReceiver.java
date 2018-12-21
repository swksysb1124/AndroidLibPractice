package crop.computer.askey.androidlib.http.remoteservice;


public interface ServiceDataReceiver {
	void onSuccess(String key, String content);
	void onFail(String key, int errorType, String errorMessage);
}
