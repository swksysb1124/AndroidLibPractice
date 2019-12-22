package com.jasonstudio.jy.androidlib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;

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

    public static String getUTCTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return Long.toString(calendar.getTimeInMillis() / 1000L);
    }

    public static String getUTCTime() {

        StringBuffer UTCTimeBuffer = new StringBuffer();

        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance() ;

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        UTCTimeBuffer.append(year)
                .append("-").append(month)
                .append("-").append(day) ;
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;

        try{
            format.parse(UTCTimeBuffer.toString()); // 確認結果是否符合Data格式
            return UTCTimeBuffer.toString() ;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
