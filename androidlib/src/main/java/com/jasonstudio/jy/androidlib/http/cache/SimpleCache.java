package com.jasonstudio.jy.androidlib.http.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class SimpleCache implements CacheManager.Cache<CacheData> {

    private static final String SP_CACHE_API_DATA = "sp.cache.api.data";
    private SharedPreferences mPreference;

    public SimpleCache(Context mContext) {
        mPreference = mContext.getSharedPreferences(SP_CACHE_API_DATA, Context.MODE_PRIVATE);
    }

    public static SimpleCache newInstance(Context mContext) {
        return new SimpleCache(mContext);
    }

    @Nullable
    @Override
    public CacheData getData(@NonNull String url) {
        String rowData = mPreference.getString(url, null);
        return deserializeCacheData(rowData);
    }

    @Override
    public void putData(@NonNull String url, @NonNull CacheData data) {

        String serializedData = serializeCacheData(data);

        if(getMaxCount() == 0) return;

        if (mPreference.getAll().size() < getMaxCount()) {
            mPreference.edit()
                    .putString(url, serializedData)
                    .apply();
        } else {
            String key = mPreference.getAll().keySet().iterator().next();
            mPreference.edit()
                    .remove(key)
                    .putString(url, serializedData)
                    .apply();
        }
    }

    @Override
    public int getMaxCount() {
        return 1;
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
        Log.w("SimpleCacheManager", "deserialize: rowDate=" + rowData);
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
