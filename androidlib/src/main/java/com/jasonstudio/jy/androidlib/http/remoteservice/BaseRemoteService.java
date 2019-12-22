package com.jasonstudio.jy.androidlib.http.remoteservice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.request.HttpRequest;
import com.jasonstudio.jy.androidlib.http.request.HttpsRequest;
import com.jasonstudio.jy.androidlib.http.request.QueryAttribute;
import com.jasonstudio.jy.androidlib.http.request.Request;
import com.jasonstudio.jy.androidlib.http.request.RequestCallback;
import com.jasonstudio.jy.androidlib.http.request.RequestManager;
import com.jasonstudio.jy.androidlib.http.response.Response;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.URLInfo;


public abstract class BaseRemoteService
        implements RemoteService {

    private RequestManager mRequestManager;

    private URLConfigManager mURLConfigManager;

    protected ServiceDataReceiver dataListener;

    public void registerDataReceiver(ServiceDataReceiver dataListener) {
        this.dataListener = dataListener;
    }

    public void unregisterDataReceiver() {
        this.dataListener = null;
    }

    @NonNull
    protected abstract RequestManager injectRequestManager();

    @NonNull
    protected abstract URLConfigManager injectURLConfigManager();

    @Nullable
    protected abstract String interceptURLString(String url);


    protected void invoke(final String key,
                          List<HeaderField> headers,
                          List<QueryAttribute> queries,
                          final String requestBody) {
        invoke(key, headers, queries, requestBody, this);
    }

    @Override
    public void invoke(@NonNull final String key,
                       @Nullable List<HeaderField> headers,
                       @Nullable List<QueryAttribute> queries,
                       @Nullable final String requestBody,
                       @NonNull RequestCallback callback) {

        final URLInfo urlInfo = getURLConfigManager().findURL(key);

        String url = generateDefaultURLString(urlInfo);
        if (interceptURLString(url) != null) {
            url = interceptURLString(url);
        }
        if (queries != null) {
            url = addRequestParameter(url, queries);
        }

        invoke(key, urlInfo.getMethod(), url, headers, requestBody, callback);
    }

    public void cancelRequst() {
        if (getRequestManager() != null) {
            getRequestManager().cancelRequests();
        }
    }

    @Override
    public void invoke(@NonNull String key,
                       @NonNull String method,
                       @NonNull String url,
                       @Nullable List<HeaderField> headers,
                       @Nullable String requestBody,
                       @NonNull RequestCallback callback) {

        Request request;
        if (url.startsWith("https")) {
            request = new HttpsRequest(url);
        } else {
            request = new HttpRequest(url);
        }
        request.setKey(key);
        request.setMethod(method);
        request.setRqProperties(headers);
        request.setRqParams(null);
        request.setBody(requestBody);
        request.setCallback(callback);

        getRequestManager().start();
        getRequestManager().execute(request);
    }

    private String generateDefaultURLString(URLInfo urlInfo) {
        StringBuilder builder = new StringBuilder();
        if (urlInfo == null) {
            System.out.println("urlInfo == null");
        }
        builder.append(urlInfo.getScheme());
        builder.append(urlInfo.getHost());
        builder.append(urlInfo.getPath());
        return builder.toString();
    }

    private String addRequestParameter(@Nullable String urlStr, @NonNull List<QueryAttribute> rqParams) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(urlStr);
        if (!rqParams.isEmpty()) {
            urlBuilder.append("?");
            for (int i = 0; i < rqParams.size(); i++) {
                QueryAttribute param = rqParams.get(i);
                urlBuilder.append(param.key);
                urlBuilder.append("=");
                urlBuilder.append(param.value);
                if (i != rqParams.size() - 1) {
                    urlBuilder.append("&");
                }
            }
        }
        return urlBuilder.toString();
    }

    @Override
    public void onSuccess(String key, String url, Response response, String content) {
        if (dataListener != null) {
            dataListener.onSuccess(key, content);
        }
    }

    @Override
    public void onFail(String key, String url, Response response, int errorType, String errorMessage) {
        if (dataListener != null) {
            dataListener.onFail(key, errorType, errorMessage);
        }
    }

    public RequestManager getRequestManager() {
        if (mRequestManager == null) {
            mRequestManager = injectRequestManager();
        }
        return mRequestManager;
    }

    public URLConfigManager getURLConfigManager() {
        if (mURLConfigManager == null) {
            mURLConfigManager = injectURLConfigManager();
        }
        return mURLConfigManager;
    }

    @Override
    public void finish() {
        if (mRequestManager != null) {
            mRequestManager.finish();
            mRequestManager = null;
        }
        if (mURLConfigManager != null) {
            mURLConfigManager = null;
        }
        if (dataListener != null) {
            dataListener = null;
        }
    }
}
