package com.tr.muhurath.app.muhurat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bkatika on 1/31/16.
 */
public class SunRiseSetUtil {

    /**
     * Get the SunRise for the given date
     * @param date - input date
     * @return - Date with time specifying the Sun Rise
     */
    public static Date getSunRise(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,06);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);
        Date ret = cal.getTime();
        return ret;
    }

    /**
     * Get the SunSet for the given date
     * @param date - input date
     * @return - Date with time specifying the Sun Set
     */
    public static Date getSunSet(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,18);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);
        Date ret = cal.getTime();
        return ret;
    }
}
