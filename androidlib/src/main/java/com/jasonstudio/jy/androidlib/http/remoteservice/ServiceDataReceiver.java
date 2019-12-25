package com.jasonstudio.jy.androidlib.http.remoteservice;


public interface ServiceDataReceiver {
	void onSuccess(String key, String content);
	void onFail(String key, int errorType, String errorMessage);
	void onDisconnected(String key, String errorMessage);
	void onTimeout(String key, String errorMessage);
	void onUnknownHost(String key, String errorMessage);
}
