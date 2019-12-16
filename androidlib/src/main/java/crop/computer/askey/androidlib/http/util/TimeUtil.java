package crop.computer.askey.androidlib.http.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    private static final String DATA_FORMAT_PATTERN = "yyyy-mm-dd hh:mm:ss";

    public static String getCurrentTimeString() {
        return format(getCurrentTime());
    }

    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    public static String format(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT_PATTERN, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(time);
    }

    public static Date parse(String timeStr) {
        return parse(timeStr, getCurrentTime());
    }

    public static Date parse(String timeStr, Date fallback) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT_PATTERN, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        Date time = fallback;
        try {
            time = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
