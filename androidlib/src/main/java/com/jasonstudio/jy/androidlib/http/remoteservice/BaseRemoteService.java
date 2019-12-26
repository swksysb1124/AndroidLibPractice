package com.jasonstudio.jy.androidlib.http.remoteservice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.request.HttpRequest;
import com.jasonstudio.jy.androidlib.http.request.QueryAttribute;
import com.jasonstudio.jy.androidlib.http.request.Request;
import com.jasonstudio.jy.androidlib.http.request.RequestManager;
import com.jasonstudio.jy.androidlib.http.response.Response;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.URLInfo;


public abstract class BaseRemoteService
        implements RemoteService {

    private RequestManager mRequestManager;

    private URLConfigManager mURLConfigManager;

    protected ServiceDataReceiver dataListener;

    public void registerDataReceiver(@NonNull ServiceDataReceiver dataListener) {
        this.dataListener = dataListener;
    }

    public void unregisterDataReceiver() {
        this.dataListener = null;
    }

    @NonNull
    protected abstract RequestManager injectRequestManager();

    @NonNull
    protected abstract URLConfigManager injectURLConfigManager();


    protected abstract String interceptURLString(String url);


    public void invoke(@NonNull final String key,
                       @Nullable List<HeaderField> headers,
                       @Nullable List<QueryAttribute> queries,
                       @Nullable final String requestBody) {

        invoke(key, null, headers, queries, requestBody);
    }

    public void invoke(@NonNull final String key,
                       @Nullable Map<String, String> pathVars,
                       @Nullable List<HeaderField> headers,
                       @Nullable List<QueryAttribute> queries,
                       @Nullable final String requestBody) {

        final URLInfo urlInfo = getURLConfigManager().findURL(key);

        String url = generateDefaultURLString(urlInfo);
        if (interceptURLString(url) != null) {
            url = interceptURLString(url);
        }

        if (pathVars != null) {
            url = embedPathVars(url, pathVars);
        }

        if (queries != null) {
            url = addRequestParameter(url, queries);
        }

        invoke(key, urlInfo.getMethod(), url, headers, requestBody);

    }

    @Override
    public void invoke(@NonNull String key,
                       @NonNull String method,
                       @NonNull String url,
                       @Nullable List<HeaderField> headers,
                       @Nullable String requestBody) {

        invoke(key, method, url, headers, requestBody, 30000);
    }

    private void invoke(@NonNull String key,
                       @NonNull String method,
                       @NonNull String url,
                       @Nullable List<HeaderField> headers,
                       @Nullable String requestBody, int timeout) {

        Request request = new HttpRequest(url);

        request.setKey(key);
        request.setTimeout(timeout);
        request.setMethod(method);
        request.setRqProperties(headers);
        request.setRqParams(null);
        request.setBody(requestBody);
        request.setCallback(this);

        getRequestManager().start();
        getRequestManager().execute(request);
    }

    public void cancelRequst() {
        if (getRequestManager() != null) {
            getRequestManager().cancelRequests();
        }
    }

    @NonNull
    private String generateDefaultURLString(@NonNull URLInfo urlInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append(urlInfo.getScheme());
        builder.append(urlInfo.getHost());
        builder.append(urlInfo.getPath());
        return builder.toString();
    }

    private String embedPathVars(@NonNull String url, @NonNull Map<String, String> pathVars) {
        if(pathVars.isEmpty()) {
            return url;
        }
        for (String pathKey: pathVars.keySet()) {
            String pathValue = pathVars.get(pathKey);
            if(pathValue != null) {
                url = url.replace(pathKey, pathValue);
            }
        }
        return url;
    }

    private String addRequestParameter(@NonNull String urlStr, @NonNull List<QueryAttribute> rqParams) {
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

    @Override
    public void onUnknownHost(String key, String url, String errorMessage) {
        if (dataListener != null){
            dataListener.onUnknownHost(key, errorMessage);
        }
    }

    @Override
    public void onDisconnected(String key, String url, String errorMessage) {
        if (dataListener != null){
            dataListener.onDisconnected(key, errorMessage);
        }
    }

    @Override
    public void onTimeout(String key, String url, String errorMessage) {
        if (dataListener != null){
            dataListener.onTimeout(key, errorMessage);
        }
    }

    public synchronized RequestManager getRequestManager() {
        if (mRequestManager == null) {
            mRequestManager = injectRequestManager();
        }
        return mRequestManager;
    }

    public synchronized URLConfigManager getURLConfigManager() {
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
