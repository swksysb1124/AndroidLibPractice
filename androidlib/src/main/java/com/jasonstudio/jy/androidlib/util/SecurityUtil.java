package com.jasonstudio.jy.androidlib.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String getMD2(String str){
        return getHash(str, "MD2");
    }

    public static String getMD5(String str){
        return getHash(str, "MD5");
    }

    public static String getSHA1(String str){
        return getHash(str, "SHA-1");
    }

    public static String getSHA256(String str){
        return getHash(str, "SHA-256");
    }

    public static String getSHA384(String str){
        return getHash(str, "SHA-384");
    }

    public static String getSHA512(String str){
        return getHash(str, "SHA-512");
    }

    private static String getHash(String str, String parameter) {
        // parameter: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
        try {
            MessageDigest digest = MessageDigest.getInstance(parameter);
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();
            for(byte b: hash){
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        } catch (Exception var8) {
            var8.printStackTrace();
        }
        return null;
    }

    public static String encodeBase64(String original) {
        byte[] oriByte = original.getBytes(StandardCharsets.UTF_8);
        byte[] encByte = android.util.Base64.encode(oriByte, android.util.Base64.DEFAULT);
        return new String(encByte, StandardCharsets.UTF_8);
    }

    public static String decodeBase64(String encoded) {
        byte[] encByte = encoded.getBytes(StandardCharsets.UTF_8);
        byte[] decByte = android.util.Base64.decode(encByte, android.util.Base64.DEFAULT);
        return new String(decByte, StandardCharsets.UTF_8);
    }
}
