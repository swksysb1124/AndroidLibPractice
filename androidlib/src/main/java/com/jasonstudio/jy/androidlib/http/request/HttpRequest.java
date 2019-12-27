package com.jasonstudio.jy.androidlib.http.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jasonstudio.jy.androidlib.http.response.Response;

import javax.net.ssl.HttpsURLConnection;


public class HttpRequest extends Request {

    @Nullable
    private HttpsCertificate certificate;

    public HttpRequest(String url) {
        super(url);
    }

    private HttpRequest(Builder builder) {
        super(builder.url);
        setKey(builder.key);
        setMethod(builder.method);
        setHeaderFields(builder.rqProperties);
        setBody(builder.body);
        setCallback(builder.callback);
        setTimeout(builder.timeout);
        setCertificate(builder.certificate);
    }

    private void setCertificate(@Nullable HttpsCertificate certificate) {
        this.certificate = certificate;
    }

    @Override
    protected Response getResponse(String urlStr, String method, List<HeaderField> rqProperties, String body, int timeout) {

        HttpURLConnection connection = null;

        if (method == null) {
            method = "GET";
        }

        Response response = new Response();
        try {
            URL url = new URL(urlStr);

            if (urlStr.startsWith("https://")) {
                connection = (HttpsURLConnection) url.openConnection();
                if (certificate != null) {
                    certificate.setCertificate((HttpsURLConnection) connection);
                }
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setRequestMethod(method);

            addRequestProperty(connection, rqProperties);

            setTimeout(connection, timeout);

            if (method.equals("POST") || method.equals("PUT")) {
                connection.setDoOutput(true);
                writeBodyToConnection(getBody(), connection);
            }

            int statusCode = connection.getResponseCode();
            response.setStatusCode(statusCode);

            if (statusCode == HttpURLConnection.HTTP_OK) {
                List<HeaderField> headerFields = extractHeaders(connection);
                response.setHeaders(headerFields);

                InputStream inputStream = connection.getInputStream();
                String result = getResponseBody(inputStream);
                setSuccessResponse(result, response);
            } else {
                InputStream inputStream = connection.getErrorStream();
                String errorMessage = getResponseBody(inputStream);
                setErrorResponse(statusCode, errorMessage, response);
            }

        } catch (SocketTimeoutException se) {
            setErrorResponse(Response.SOCKET_TIMEOUT_EXCEPTION_ERROR,
                    se.toString(), response);
        } catch (SocketException se) {
            setErrorResponse(Response.SOCKET_EXCEPTION_ERROR,
                    se.toString(), response);
        } catch (UnknownHostException ue) {
            setErrorResponse(Response.UNKNOWN_HOST_EXCEPTION_ERROR,
                    ue.toString(), response);
        } catch (IOException e) {
            setErrorResponse(Response.IO_EXCEPTION_ERROR,
                    e.toString(), response);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }


    private List<HeaderField> extractHeaders(@NonNull HttpURLConnection connection) {
        List<HeaderField> headerFields = new ArrayList<>();
        Map<String, List<String>> map = connection.getHeaderFields();
        for (String key : map.keySet()) {
            if (key == null) continue;
            List<String> list = map.get(key);
            if (list != null) {
                StringBuilder builder = new StringBuilder();
                for (String str : list) {
                    builder.append(str).append(";");
                }
                String header = builder.toString();
                headerFields.add(new HeaderField(key, header));
            }
        }
        return headerFields;
    }

    private void writeBodyToConnection(@NonNull final String requestBody, @NonNull final HttpURLConnection connection)
            throws IOException {
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.write(requestBody.getBytes(StandardCharsets.UTF_8));
        dos.flush();
        dos.close();
    }

    private void addRequestProperty(final HttpURLConnection connection, List<HeaderField> rqProperties) {
        if (rqProperties == null || rqProperties.isEmpty()) {
            return;
        }

        for (HeaderField property : rqProperties) {
            connection.setRequestProperty(property.key, property.value);
        }
    }

    private void setTimeout(final HttpURLConnection connection, int timeout) {
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
    }

    private String getResponseBody(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        byte[] result = out.toByteArray();
        out.close();
        return new String(result);
    }

    private void setSuccessResponse(String result, final Response response) {
        response.setError(false);
        response.setErrorType(0);
        response.setErrorMessage("");
        response.setResult(result);
    }

    private void setErrorResponse(int type, String message, final Response response) {
        response.setError(true);
        response.setErrorType(type);
        response.setErrorMessage(message);
        response.setResult("");
    }


    public static final class Builder {
        private String key;
        private String url;
        private String method;
        private List<HeaderField> rqProperties;
        private String body;
        private Request.Callback callback;
        private int timeout;
        private HttpsCertificate certificate;

        public Builder(String key, String url, String method) {
            this.key = key;
            this.url = url;
            this.method = method;
        }

        public Builder setRqProperties(List<HeaderField> rqProperties) {
            this.rqProperties = rqProperties;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setCallback(Request.Callback callback) {
            this.callback = callback;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setCertificate(HttpsCertificate certificate) {
            this.certificate = certificate;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
