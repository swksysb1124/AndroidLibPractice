package crop.computer.askey.androidlib.http.url;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class URLInfo {

    @NonNull
    private String key;

    @NonNull
    private String method;

    @NonNull
    private String scheme;

    @NonNull
    private String host;

    @Nullable
    private String port;

    @NonNull
    private String path;

    private long expires; // cache使用

    public URLInfo(@NonNull String key, long expires, @NonNull String method, @NonNull String scheme, @NonNull String host, @Nullable String port, @NonNull String path) {
        this.key = key;
        this.expires = expires;
        this.method = method;
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    @NonNull
    public String getHost() {
        return host;
    }

    public void setHost(@Nullable String host) {
        this.host = host;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @NonNull
    public String getMethod() {
        return method;
    }

    public void setMethod(@NonNull String method) {
        this.method = method;
    }

    @NonNull
    public String getPath() {
        return path;
    }

    public void setPath(@NonNull String path) {
        this.path = path;
    }

    @NonNull
    public String getScheme() {
        return scheme;
    }

    public void setScheme(@NonNull String scheme) {
        this.scheme = scheme;
    }

    @Nullable
    public String getPort() {
        return port;
    }

    public void setPort(@Nullable String port) {
        this.port = port;
    }

    @NonNull
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("key: ").append(key).append("\n");
        builder.append("method: ").append(method).append("\n");
        builder.append("scheme: ").append(scheme).append("\n");
        builder.append("host: ").append(host).append("\n");
        builder.append("port: ").append(port).append("\n");
        builder.append("path: ").append(path).append("\n");
        builder.append("expires: ").append(expires).append("\n");
        return builder.toString();
    }

    @NonNull
    public String toURLString() {
        String url = "";
        url += scheme;
        url += "://";
        url += host;
        if (port != null) {
            url += ":";
            url += port;
        }
        url += path;
        return url;
    }

}
