package com.jasonstudio.jy.androidlib.http.remoteservice;

public interface ServiceCallback {
	void onDataReceivedSuccess(String data);
	void onDataReceivedError(int type, String error);
}
