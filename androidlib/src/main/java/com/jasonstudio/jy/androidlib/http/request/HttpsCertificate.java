package com.jasonstudio.jy.androidlib.http.request;

import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsCertificate {

    public SSLSocketFactory oldSSLSocketFactory;
    public HostnameVerifier oldHostnameVerifier;

    public void setCertificate(final HttpsURLConnection https) {
        oldSSLSocketFactory = trustAllHosts(https);
        oldHostnameVerifier = https.getHostnameVerifier();
        https.setHostnameVerifier(getHostnameVerifier());
    }

    private SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, getTrustManagers(), new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

    protected HostnameVerifier getHostnameVerifier() {
        return DO_NOT_VERIFY;
    }

    protected TrustManager[] getTrustManagers() {
        return trustAllCerts;
    }

    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        @Override
        public boolean verify(String paramString, SSLSession paramSSLSession) {
            return true;
        }

    };

    private static TrustManager[] trustAllCerts = new TrustManager[]{

            new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }

                @Override
                public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    // TODO Auto-generated method stub
                    return new X509Certificate[]{};
                }

            }
    };
}
