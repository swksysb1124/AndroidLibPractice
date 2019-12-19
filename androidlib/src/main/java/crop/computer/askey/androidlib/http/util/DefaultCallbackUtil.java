package crop.computer.askey.androidlib.http.util;

import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.response.Response;

public class DefaultCallbackUtil {
	public static void callback(String key, String url, Response response, RequestCallback callback) {
		if(callback != null) {
			if(!response.hasError()) {
				callback.onSuccess(key, url, response, response.getResult());
			}else {
				callback.onFail(key, url, response, response.getErrorType(), response.getErrorMessage());
			}
		}
	}
}
