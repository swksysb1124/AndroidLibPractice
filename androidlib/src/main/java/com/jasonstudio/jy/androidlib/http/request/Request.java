package com.jasonstudio.jy.androidlib.http.request;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.response.Response;

public abstract class Request
        implements Runnable {

    private String key;
    private String url;
    private String method;
    private List<HeaderField> rqProperties;
    private List<QueryAttribute> rqParams;
    private String body;
    private RequestCallback callback;
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

    public List<HeaderField> getRqProperties() {
        return rqProperties;
    }

    public void setRqProperties(List<HeaderField> rqProperties) {
        this.rqProperties = rqProperties;
    }

    public List<QueryAttribute> getRqParams() {
        return rqParams;
    }

    public void setRqParams(List<QueryAttribute> rqParams) {
        this.rqParams = rqParams;
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

    public void setCallback(RequestCallback callback) {
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
    public RequestCallback getCallback() {
        return callback;
    }

    @Override
    public void run() {
        Response response = getResponse(url, method, rqProperties, rqParams, body, timeout);
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
                                            List<QueryAttribute> rqParams,
                                            String body,
                                            int timeout);

}
