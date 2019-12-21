package com.jasonstudio.jy.androidlib.http.util;

import com.jasonstudio.jy.androidlib.http.request.RequestCallback;
import com.jasonstudio.jy.androidlib.http.response.Response;

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
