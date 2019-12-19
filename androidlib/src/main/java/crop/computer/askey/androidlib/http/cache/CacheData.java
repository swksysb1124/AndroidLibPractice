package crop.computer.askey.androidlib.http.cache;

import android.support.annotation.NonNull;

import java.util.Date;

import crop.computer.askey.androidlib.http.util.TimeUtil;

public class CacheData {
    @NonNull private String url;
    @NonNull private String data;
    private long time;
    private long expire; // unit: millisecond

    public CacheData(@NonNull String url, long time, @NonNull String data, long expire) {
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

    public long getTime() {
        return time;
    }

    public long getExpire() {
        return expire;
    }

    public boolean isExpired(long currentTime) {
        return (currentTime - time) > expire;
    }

    @Override
    public String toString() {
        String str = "";
        str+="url: "+url+"\n";
        str+="time: "+time+"\n";
        str+="data: "+data+"\n";
        str+="expire: "+expire;
        return str;
    }
}
