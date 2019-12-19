package crop.computer.askey.androidlib.http.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import crop.computer.askey.androidlib.http.cache.CacheData;
import crop.computer.askey.androidlib.http.cache.CacheManager;
import crop.computer.askey.androidlib.http.cache.CacheManagerImp;
import crop.computer.askey.androidlib.http.remoteservice.BaseRemoteService;
import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.HttpRequest;
import crop.computer.askey.androidlib.http.request.HttpsRequest;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.Request;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.response.Response;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.URLInfo;
import crop.computer.askey.androidlib.http.util.TimeUtil;

public abstract class BaseCacheRemoteService extends BaseRemoteService {

    private CacheManager<CacheData> cacheDataCacheManager;

    @Nullable
    protected abstract CacheManager<CacheData> injectCacheManager();

    @Nullable
    private CacheManager<CacheData> getCacheDataCacheManager() {
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
                       @Nullable String requestBody,
                       @NonNull RequestCallback callback) {
        final CacheManager<CacheData> cacheManager = getCacheDataCacheManager();

        if ("GET".equals(method)) {
            if (cacheManager != null) {
                Log.w("BaseCacheRemoteService", "cache support");
                CacheData cacheData = cacheManager.getData(url);

                if (cacheData != null) {

                    Log.w("BaseCacheRemoteService", "cacheData existed");
                    Log.w("BaseCacheRemoteService", "cacheData = "+cacheData.toString());
                    
                    if (!cacheData.isExpired(TimeUtil.getCurrentTime())) {
                        Log.w("BaseCacheRemoteService", "cacheData not expired");
                        dataListener.onSuccess(key, "[cached] " + cacheData.getData());
                        return;
                    }else {
                        Log.w("BaseCacheRemoteService", "cacheData expired");
                    }

                }
            }
        }

        Log.e("BaseCacheRemoteService", "invoke api: " + key);
        super.invoke(key, method, url, headers, requestBody, callback);
    }

    @Override
    public void onSuccess(String key, String url, Response response, String content) {
        final CacheManager<CacheData> cacheManager = getCacheDataCacheManager();
        final URLConfigManager urlConfigManager = getURLConfigManager();
        URLInfo urlInfo = urlConfigManager.findURL(key);

        cacheApiData(url, content, cacheManager, urlInfo);

        Log.w("BaseCacheRemoteService", "onSuccess()");
        super.onSuccess(key, url, response, content);
    }

    @Override
    public void onFail(String key, String url, Response response, int errorType, String errorMessage) {
        super.onFail(key, url, response, errorType, errorMessage);
        Log.w("BaseCacheRemoteService", "onFail()");
    }

    private void cacheApiData(String url, String content, CacheManager<CacheData> cacheManager, URLInfo urlInfo) {
        if (cacheManager != null
                && urlInfo.getExpires() > 0) {
            Log.w("BaseCacheRemoteService", "caching data");

            CacheData cachedData =
                    new CacheData(url, TimeUtil.getCurrentTime(), content, urlInfo.getExpires());
            cacheManager.putData(url, cachedData);
        }
    }
}
