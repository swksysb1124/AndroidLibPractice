package crop.computer.askey.androidlib.http.util;

import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.response.Response;

public class DefaultCallbackUtil {
	public static void callback(String key, Response response, RequestCallback callback) {
		if(callback != null) {
			if(!response.hasError()) {
				callback.onSuccess(key, response, response.getResult());
			}else {
				callback.onFail(key, response, response.getErrorType(), response.getErrorMessage());
			}
		}
	}
}
