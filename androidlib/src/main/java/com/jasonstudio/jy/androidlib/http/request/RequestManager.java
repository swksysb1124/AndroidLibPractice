package com.jasonstudio.jy.androidlib.http.request;

public interface RequestManager {
	void start();
	void execute(Request request);
	void cancelRequests();
	void finish();
}
