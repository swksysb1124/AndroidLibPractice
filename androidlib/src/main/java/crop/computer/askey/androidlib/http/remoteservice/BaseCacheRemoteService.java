package crop.computer.askey.androidlib.http.remoteservice;

import java.util.List;

import crop.computer.askey.androidlib.http.cache.CacheData;
import crop.computer.askey.androidlib.http.cache.CacheManager;
import crop.computer.askey.androidlib.http.cache.CacheManagerImp;
import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.HttpRequest;
import crop.computer.askey.androidlib.http.request.HttpsRequest;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.Request;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.URLInfo;

public abstract class BaseCacheRemoteService extends BaseRemoteService {

    private CacheManager<CacheData> cacheDataCacheManager;

    protected abstract CacheManager<CacheData> injectCacheManager();

    private CacheManager<CacheData> getCacheDataCacheManager() {
        if(cacheDataCacheManager == null) {
            cacheDataCacheManager = injectCacheManager();
        }
        return cacheDataCacheManager;
    }


    @Override
    public void invoke(String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams, String requestBody, RequestCallback callback) {


    }
}
