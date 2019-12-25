package com.jasonstudio.jy.androidlibpractice.service.ap5630w;

public interface Ap5630wMobileAPI {
    void login(String username, String password);
    void logout();
    void reboot();
    void setAccountPassword(String password);
    void setWifiSettingReload();
    void setWifiSettingReloadWlan();
    void setWifiSetting(String ssid, String pwd, int encryption);
    void getEncryptionOptions();
    void getOnboardStatus();
    void setOnboardStatus(String action);
    void getGlobalData();
    void getCurrentData();
    void getCurrentDataWifi();
    void getDeviceCount();
    void getClientList();
    void getClientList(int startIndex, int endIndex);
    void getTargetClientInfo(String mac);
    void getMeshList();
    void getTargetMeshInfo(String mac);
}
