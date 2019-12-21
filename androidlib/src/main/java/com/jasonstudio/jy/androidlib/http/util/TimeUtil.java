package com.jasonstudio.jy.androidlib.http.util;

import java.util.Date;

public class TimeUtil {

    public static long getCurrentTime() {
        return new Date().getTime();
    }

    public static String format(long time) {
        return parse(time, new Date()).toString();
    }

    public static Date parse(long time, Date fallback) {
        Date date = fallback;
        try {
            date = new Date(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
