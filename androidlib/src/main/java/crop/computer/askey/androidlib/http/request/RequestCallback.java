package crop.computer.askey.androidlib.http.request;

import crop.computer.askey.androidlib.http.response.Response;

public interface RequestCallback {
	void onSuccess(String key, String url, Response response, String content);
	void onFail(String key, String url, Response response, int errorType, String errorMessage);
}
