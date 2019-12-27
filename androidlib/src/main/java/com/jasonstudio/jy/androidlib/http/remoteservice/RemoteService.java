package com.jasonstudio.jy.androidlib.http.remoteservice;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.request.Request;

public interface RemoteService extends Request.Callback {
	
	void finish();
	void invoke(String key, String method, String url, List<HeaderField> headers, String requestBody, int timeout);
}
