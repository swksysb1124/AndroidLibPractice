package com.jasonstudio.jy.androidlibpractice.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.jasonstudio.jy.androidlib.http.remoteservice.BaseRemoteService;
import com.jasonstudio.jy.androidlib.http.request.HeaderField;
import com.jasonstudio.jy.androidlib.http.request.QueryAttribute;
import com.jasonstudio.jy.androidlib.http.request.RequestManager;
import com.jasonstudio.jy.androidlib.http.request.ThreadRequestManager;
import com.jasonstudio.jy.androidlib.http.url.URLConfigManager;
import com.jasonstudio.jy.androidlib.http.url.URLInfo;
import com.jasonstudio.jy.androidlib.http.url.XmlURLConfigManager;

/**
 * Created by weikai on 2018/12/21.
 */

public class RemoteServiceImp
        extends BaseRemoteService
        implements WebServiceAPI {

    private Context mContext;

    private RemoteServiceImp(Context context) {
        mContext = context;
    }

    private static RemoteServiceImp instance;

    public static RemoteServiceImp getInstance(final Context context) {
        if(instance == null) {
            synchronized (RemoteServiceImp.class) {
                if(instance == null) {
                    instance = new RemoteServiceImp(context);
                }
            }
        }
        return instance;
    }


    @Override
    protected RequestManager injectRequestManager() {
        return new ThreadRequestManager();
    }

    @Override
    protected URLConfigManager injectURLConfigManager() {
        return new XmlURLConfigManager(mContext, "url.xml");
    }

    @Override
    protected String interceptURLString(String url) {
        return null;
    }

    @Override
    public void testGET(String att1, String att2) {
        List<QueryAttribute> qas = new ArrayList<>();
        qas.add(new QueryAttribute("k1",att1));
        qas.add(new QueryAttribute("k1",att2));

        invoke("testGET", null, qas, null);
    }

    @Override
    public void testPOST(String body) {
        List<HeaderField> hfs = new ArrayList<>();
        hfs.add(new HeaderField("Content-Type","application/json"));

        invoke("testPOST", hfs, null, body);
    }
}
