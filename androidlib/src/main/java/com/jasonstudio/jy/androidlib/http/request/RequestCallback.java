package com.jasonstudio.jy.androidlib.http.request;

import com.jasonstudio.jy.androidlib.http.response.Response;

public interface RequestCallback {
	void onSuccess(String key, String url, Response response, String content);
	void onFail(String key, String url, Response response, int errorType, String errorMessage);
	void onDisconnected(String key, String url, String errorMessage);
	void onTimeout(String key, String url, String errorMessage);
	void onUnknownHost(String key, String url, String errorMessage);
}
