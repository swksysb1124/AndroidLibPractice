package crop.computer.askey.androidlib.http.cache;

import java.util.Date;

public class CacheData {
    private String url;
    private Date time;
    private String data;
    private int expire; // unit: millisecond

    public CacheData(String url, Date time, String data, int expire) {
        this.url = url;
        this.time = time;
        this.data = data;
        this.expire = expire;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public Date getTime() {
        return time;
    }

    public int getExpire() {
        return expire;
    }

    public boolean isExpired(Date currentTime) {
        return (currentTime.getTime() - time.getTime()) > expire;
    }
}
