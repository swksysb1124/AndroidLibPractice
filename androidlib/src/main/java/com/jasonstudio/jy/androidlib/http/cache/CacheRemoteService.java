package com.jasonstudio.jy.androidlib.http.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import com.jasonstudio.jy.androidlib.http.remoteservice.BaseRemoteService;
import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.response.Response;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.URLInfo;
import com.jasonstudio.jy.androidlib.util.TimeUtil;

public abstract class CacheRemoteService extends BaseRemoteService {

    private CacheManager<CacheData> cacheDataCacheManager;

    @Nullable
    protected abstract CacheManager<CacheData> injectCacheManager();

    @Nullable
    private synchronized CacheManager<CacheData> getCacheDataCacheManager() {
        if (cacheDataCacheManager == null) {
            cacheDataCacheManager = injectCacheManager();
        }
        return cacheDataCacheManager;
    }

    @Override
    public void invoke(@NonNull String key,
                       @NonNull String method,
                       @NonNull String url,
                       @Nullable List<HeaderField> headers,
                       @Nullable String requestBody) {

        final CacheManager<CacheData> cacheManager = getCacheDataCacheManager();

        if ("GET".equals(method)) {
            if (cacheManager != null) {
                Log.w("CacheRemoteService", "cache support");
                CacheData cacheData = cacheManager.getData(url);

                if (cacheData != null) {

                    Log.w("CacheRemoteService", "cacheData existed");
                    Log.w("CacheRemoteService", "cacheData = " + cacheData.toString());

                    if (!cacheData.isExpired(TimeUtil.getCurrentTime())) {
                        Log.w("CacheRemoteService", "cacheData not expired");
                        dataListener.onSuccess(key, "[cached] " + cacheData.getData());
                        return;
                    } else {
                        Log.w("CacheRemoteService", "cacheData expired");
                    }

                }
            }
        }

        Log.e("CacheRemoteService", "invoke api: " + key);
        super.invoke(key, method, url, headers, requestBody);
    }

    @Override
    public void onSuccess(String key, String url, Response response, String content) {
        final CacheManager<CacheData> cacheManager = getCacheDataCacheManager();
        final URLConfigManager urlConfigManager = getURLConfigManager();
        URLInfo urlInfo = urlConfigManager.findURL(key);

        cacheApiData(url, content, cacheManager, urlInfo);

        Log.w("CacheRemoteService", "onSuccess()");
        super.onSuccess(key, url, response, content);
    }

    private void cacheApiData(@NonNull String url,
                              @NonNull String content,
                              @Nullable CacheManager<CacheData> cacheManager,
                              @NonNull URLInfo urlInfo) {

        if (cacheManager != null
                && urlInfo.getMethod().equals("GET")
                && urlInfo.getExpires() > 0) {

            Log.w("CacheRemoteService", "caching data");

            CacheData cachedData =
                    new CacheData(url, TimeUtil.getCurrentTime(), content, urlInfo.getExpires());
            cacheManager.putData(url, cachedData);
        }
    }
}
