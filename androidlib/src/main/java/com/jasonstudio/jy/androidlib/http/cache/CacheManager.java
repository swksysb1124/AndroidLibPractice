package com.jasonstudio.jy.androidlib.http.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface CacheManager<T> {

    @Nullable T getData(@NonNull String url);
    void putData(@NonNull String url, @NonNull T data);
}
