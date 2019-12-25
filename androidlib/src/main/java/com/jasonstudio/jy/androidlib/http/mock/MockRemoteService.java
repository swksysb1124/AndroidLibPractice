package com.jasonstudio.jy.androidlib.http.mock;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jasonstudio.jy.androidlib.http.remoteservice.BaseRemoteService;
import com.jasonstudio.jy.androidlib.http.request.HeaderField;

import java.util.List;
import java.util.Map;

public abstract class MockRemoteService extends BaseRemoteService {

    private MockManager mockManager;

    @Nullable
    protected abstract MockManager injectMockManager();

    @Nullable
    private synchronized MockManager getMockManager() {
        if (mockManager == null) {
            mockManager = injectMockManager();
        }
        return mockManager;
    }

    @Override
    public void invoke(@NonNull String key,
                       @NonNull String method,
                       @NonNull String url,
                       @Nullable List<HeaderField> headers,
                       @Nullable String requestBody) {

        MockManager mockManager = getMockManager();
        if (mockManager != null) {
            Map<String, String> dummyDataMap = mockManager.getDummyDataByApiKey();
            if (dummyDataMap.get(key) != null) {
                String dummyData = dummyDataMap.get(key);
                onSuccess(key, url, null, dummyData);
            } else {
                onFail(key, url, null, 0,
                        "[MOCK] the response of this API is undefined");
            }
        }
    }
}
