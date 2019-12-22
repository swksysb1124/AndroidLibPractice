package com.jasonstudio.jy.androidlib.http.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

public class CacheManagerImp implements CacheManager<CacheData> {

    private static final String SP_CACHE_API_DATA = "sp.cache.api.data";
    private WeakReference<Context> mContext;

    private CacheManagerImp(Context context) {
        setContext(context);
    }

    private void setContext(Context context) {
        mContext = new WeakReference<>(context);
    }

    private static CacheManagerImp INSTANCE;

    public static CacheManagerImp getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CacheManagerImp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CacheManagerImp(context);
                }
            }
        }
        if (INSTANCE.mContext.get() != context) {
            INSTANCE.setContext(context);
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public CacheData getData(@NonNull String url) {
        SharedPreferences preferences = mContext.get()
                .getSharedPreferences(SP_CACHE_API_DATA, Context.MODE_PRIVATE);
        String rowData = preferences.getString(url, null);
        return deserializeCacheData(rowData);
    }

    @Override
    public void putData(@NonNull String url, @NonNull CacheData data) {
        SharedPreferences preferences = mContext.get()
                .getSharedPreferences(SP_CACHE_API_DATA, Context.MODE_PRIVATE);
        preferences.edit().putString(url, serializeCacheData(data))
                .apply();
    }

    private String serializeCacheData(CacheData data) {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(data.getUrl()).append(";");
        sbuilder.append(data.getTime()).append(";");
        sbuilder.append(data.getData()).append(";");
        sbuilder.append(data.getExpire());
        return sbuilder.toString();
    }

    private CacheData deserializeCacheData(String rowData) {
        Log.w("CacheManagerImp", "deserialize: rowDate=" + rowData);
        if (rowData == null) {
            return null;
        }
        String[] parts = rowData.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Illegal Row Data format of caching");
        }

        String url = parts[0];

        long time = Long.parseLong(parts[1]);

        String data = parts[2];

        int expire = 0; // 0 => no caching
        try {
            expire = Integer.parseInt(parts[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CacheData(url, time, data, expire);
    }
}
