package crop.computer.askey.androidlib.http.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
