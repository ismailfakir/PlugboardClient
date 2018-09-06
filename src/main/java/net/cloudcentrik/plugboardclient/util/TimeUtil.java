package net.cloudcentrik.plugboardclient.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    public final static SimpleDateFormat PLUGBOARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ss.S'Z'");

    public static String timeNow() {
        return PLUGBOARD_DATE_FORMAT.format(new Date());
    }

    public static String formatTime(Date date) {
        return PLUGBOARD_DATE_FORMAT.format(date);
    }

    public static String formatTime(String stringDate) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatTime(date);
    }
}
