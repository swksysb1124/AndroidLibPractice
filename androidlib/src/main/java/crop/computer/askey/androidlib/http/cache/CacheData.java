package crop.computer.askey.androidlib.http.cache;

import android.support.annotation.NonNull;

import java.util.Date;

public class CacheData {
    @NonNull private String url;
    @NonNull private Date time;
    @NonNull private String data;
    private long expire; // unit: millisecond

    public CacheData(@NonNull String url, @NonNull Date time, @NonNull String data, long expire) {
        this.url = url;
        this.time = time;
        this.data = data;
        this.expire = expire;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getData() {
        return data;
    }

    @NonNull
    public Date getTime() {
        return time;
    }

    public long getExpire() {
        return expire;
    }

    public boolean isExpired(Date currentTime) {
        return (currentTime.getTime() - time.getTime()) > expire;
    }
}
