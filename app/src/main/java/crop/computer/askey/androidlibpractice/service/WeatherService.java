package crop.computer.askey.androidlibpractice.service;

import android.content.Context;
import android.support.annotation.Nullable;

import crop.computer.askey.androidlib.http.cache.BaseCacheRemoteService;
import crop.computer.askey.androidlib.http.cache.CacheData;
import crop.computer.askey.androidlib.http.cache.CacheManager;
import crop.computer.askey.androidlib.http.cache.CacheManagerImp;
import crop.computer.askey.androidlib.http.request.ExecutorRequestManager;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.URLInfo;
import crop.computer.askey.androidlib.http.url.XmlURLConfigManager;

public class WeatherService extends BaseCacheRemoteService {

    private Context context;

    public WeatherService(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    protected CacheManager<CacheData> injectCacheManager() {
        return CacheManagerImp.getInstance(context);
    }

    @Override
    protected RequestManager injectRequestManager() {
        return new ExecutorRequestManager();
    }

    @Override
    protected URLConfigManager injectURLConfigManager() {
        return new XmlURLConfigManager(context, "weather_service_url.xml");
    }

    @Override
    protected String interceptURLString(URLInfo urlInfo) {
        return null;
    }

    public void getWeatherInfo() {
        invoke("GET-WEATHER_INFO", null, null, null);
    }

}
