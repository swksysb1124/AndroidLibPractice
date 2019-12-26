package com.jasonstudio.jy.androidlib.http.cache;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class SimpleCacheManager implements CacheManager<CacheData> {

    private WeakReference<Context> mContext;
    private SimpleCache mCache;

    private SimpleCacheManager(Context context) {
        setCache(context);
    }

    private void setCache(Context context) {
        mContext = new WeakReference<>(context);
        mCache = SimpleCache.newInstance(mContext.get());
    }

    private static SimpleCacheManager INSTANCE;

    public static SimpleCacheManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SimpleCacheManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SimpleCacheManager(context);
                }
            }
        }
        if (INSTANCE.mContext.get() != context) {
            INSTANCE.setCache(context);
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public CacheData getData(@NonNull String url) {
        return getCache().getData(url);
    }

    @Override
    public void putData(@NonNull String url, @NonNull CacheData data) {
        getCache().putData(url, data);
    }

    @Override
    public Cache<CacheData> getCache() {
        return mCache;
    }
}
