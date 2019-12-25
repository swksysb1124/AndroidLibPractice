package com.jasonstudio.jy.androidlibpractice.service.ap5630w;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jasonstudio.jy.androidlib.http.mock.MockManager;
import com.jasonstudio.jy.androidlib.http.mock.MockRemoteService;
import com.jasonstudio.jy.androidlib.http.request.RequestManager;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.XmlURLConfigManager;

import java.util.HashMap;
import java.util.Map;

public class Ap5630wMobileAPIMock
        extends MockRemoteService implements Ap5630wMobileAPI {

    private Context context;

    public Ap5630wMobileAPIMock(Context context) {
        this.context = context;
    }

    public void invoke(final String key) {
        invoke(key, null, null, null);
    }

    @Override
    public void login(String username, String password) {
        invoke("GET-LOGIN");
    }

    @Override
    public void logout() {
        invoke("GET-LOGOUT");
    }

    @Override
    public void reboot() {
        invoke("GET-REBOOT");
    }

    @Override
    public void setAccountPassword(String password) {
        invoke("PUT-ACCOUNT_PWD");
    }

    @Override
    public void setWifiSettingReload() {
        invoke("GET-WIFI_BASIC_RELOAD");
    }

    @Override
    public void setWifiSettingReloadWlan() {
        invoke("GET-WIFI_BASIC_RELOAD_WLAN");
    }

    @Override
    public void setWifiSetting(String ssid, String pwd, int encryption) {
        invoke("PUT-WIFI_BASIC");
    }

    @Override
    public void getEncryptionOptions() {
        invoke("GET-ENCRY_OPTIONS");
    }

    @Override
    public void getOnboardStatus() {
        invoke("GET-ENCRY_OPTIONS");
    }

    @Override
    public void setOnboardStatus(String action) {
        invoke("PUT-ENCRY_OPTIONS");
    }

    @Override
    public void getGlobalData() {
        invoke("GET-GLOBAL_DATA");
    }

    @Override
    public void getCurrentData() {
        invoke("GET-CURRENT_DATA");
    }

    @Override
    public void getCurrentDataWifi() {
        invoke("GET-CURRENT_DATA-WIFI");
    }

    @Override
    public void getDeviceCount() {
        invoke("GET-TABLE_DATA-DEVICE_COUNT");
    }

    @Override
    public void getClientList() {
        invoke("GET-TABLE_DATA-CLIENT_LIST");
    }

    @Override
    public void getClientList(int startIndex, int endIndex) {
        invoke("PUT-TABLE_DATA-CLIENT_LIST");
    }

    @Override
    public void getTargetClientInfo(String mac) {
        invoke("PUT-TABLE_DATA-TARGET_CLIENT_INFO");
    }

    @Override
    public void getMeshList() {
        invoke("GET-TABLE_DATA-MESH_LIS");
    }

    @Override
    public void getTargetMeshInfo(String mac) {
        invoke("PUT-TABLE_DATA-TARGET_MESH_INFO");
    }

    @NonNull
    @Override
    protected RequestManager injectRequestManager() {
        return null;
    }

    @NonNull
    @Override
    protected URLConfigManager injectURLConfigManager() {
        return new XmlURLConfigManager(context, "ap5630w_mobile_api_url.xml");
    }

    @Nullable
    @Override
    protected String interceptURLString(String url) {
        return null;
    }

    @Nullable
    @Override
    protected MockManager injectMockManager() {
        return new Ap5630wMockManager();
    }
}
