package com.jasonstudio.jy.androidlib.http.remoteservice;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.request.QueryAttribute;
import com.jasonstudio.jy.androidlib.http.request.RequestCallback;


public interface RemoteService extends RequestCallback {
	
	void finish();
	void invoke(final String key, List<HeaderField> headers, List<QueryAttribute> queryAtts, final String requestBody, RequestCallback callback);
	void invoke(String key, String method, String url, List<HeaderField> headers, String requestBody, RequestCallback callback);
}
