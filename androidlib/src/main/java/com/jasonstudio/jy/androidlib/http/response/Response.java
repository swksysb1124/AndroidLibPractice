package com.jasonstudio.jy.androidlib.http.response;

import com.jasonstudio.jy.androidlib.http.request.HeaderField;

import java.util.List;

public class Response {

    public static final int IO_EXCEPTION_ERROR = 2;
    public static final int SOCKET_EXCEPTION_ERROR = 3;
    public static final int SOCKET_TIMEOUT_EXCEPTION_ERROR = 4;
    public static final int UNKNOWN_HOST_EXCEPTION_ERROR = 5;

    private int statusCode;
    private List<HeaderField> headers;
    private String result;

    private boolean isError;
    private int errorType;
    private String errorMessage;

    public Response() {}

    public Response(boolean isError, int errorType, int statusCode, String errorMessage, String result) {
        this.isError = isError;
        this.errorType = errorType;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.result = result;
    }

    private Response(Builder builder) {
        setStatusCode(builder.statusCode);
        setHeaders(builder.headers);
        setResult(builder.result);
        setError(builder.isError);
        setErrorType(builder.errorType);
        setErrorMessage(builder.errorMessage);
    }

    public boolean hasError(){
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    } 

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getErrorType(){
        return errorType;
    }

    public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isError() {
		return isError;
	}

	public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public void setHeaders(List<HeaderField> headers) {
        this.headers = headers;
    }

    public List<HeaderField> getHeaders() {
        return headers;
    }

    public static final class Builder {
        private int statusCode;
        private List<HeaderField> headers;
        private String result;
        private boolean isError;
        private int errorType;
        private String errorMessage;

        public Builder() {
        }

        public Builder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder setHeaders(List<HeaderField> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setResult(String result) {
            this.result = result;
            return this;
        }

        public Builder setIsError(boolean isError) {
            this.isError = isError;
            return this;
        }

        public Builder setErrorType(int errorType) {
            this.errorType = errorType;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
