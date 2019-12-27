package com.jasonstudio.jy.androidlib.http.request;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.response.Response;

public abstract class Request
        implements Runnable {

    private String key;
    private String url;
    private String method;
    private List<HeaderField> headerFields;
    private String body;
    private Callback callback;
    private int timeout = 30000;

    public Request(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<HeaderField> getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(List<HeaderField> headerFields) {
        this.headerFields = headerFields;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getURL() {
        return url;
    }

    public void setCallback(Request.Callback callback) {
        this.callback = callback;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Deprecated
    public Request.Callback getCallback() {
        return callback;
    }

    @Override
    public void run() {
        Response response = getResponse(url, method, headerFields, body, timeout);
        if (callback != null) {
            if (!response.hasError()) {
                callback.onSuccess(key, url, response, response.getResult());
            } else {
                switch (response.getErrorType()) {
                    case Response.UNKNOWN_HOST_EXCEPTION_ERROR:
                        callback.onUnknownHost(key, url, response.getErrorMessage());
                        break;
                    case Response.SOCKET_TIMEOUT_EXCEPTION_ERROR:
                        callback.onTimeout(key, url, response.getErrorMessage());
                        break;
                    case Response.SOCKET_EXCEPTION_ERROR:
                    case Response.IO_EXCEPTION_ERROR:
                        callback.onDisconnected(key, url, response.getErrorMessage());
                        break;
                    default:
                        callback.onFail(key, url, response, response.getErrorType(), response.getErrorMessage());
                        break;
                }
            }
        }
    }

    protected abstract Response getResponse(String urlStr,
                                            String method,
                                            List<HeaderField> rqProperties,
                                            String body,
                                            int timeout);

    public interface Callback {
        void onSuccess(String key, String url, Response response, String content);
        void onFail(String key, String url, Response response, int errorType, String errorMessage);
        void onDisconnected(String key, String url, String errorMessage);
        void onTimeout(String key, String url, String errorMessage);
        void onUnknownHost(String key, String url, String errorMessage);
    }
}
