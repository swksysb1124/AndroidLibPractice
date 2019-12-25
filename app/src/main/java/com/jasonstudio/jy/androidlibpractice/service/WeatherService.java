package com.jasonstudio.jy.androidlibpractice.service;

import android.content.Context;
import android.support.annotation.Nullable;

import com.jasonstudio.jy.androidlib.http.cache.CacheRemoteService;
import com.jasonstudio.jy.androidlib.http.cache.CacheData;
import com.jasonstudio.jy.androidlib.http.cache.CacheManager;
import com.jasonstudio.jy.androidlib.http.cache.CacheManagerImp;
import com.jasonstudio.jy.androidlib.http.request.ExecutorRequestManager;
import com.jasonstudio.jy.androidlib.http.request.RequestManager;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.XmlURLConfigManager;

public class WeatherService extends CacheRemoteService {

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
    protected String interceptURLString(String url) {
        return null;
    }

    public void getWeatherInfo() {
        invoke("GET-WEATHER_INFO", null, null, null);
    }

}
