package com.tr.muhurath.app.muhurat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bkatika on 1/31/16.
 */
public class DateUtils {
    private final static String DISPLAY_FORMAT = "hh-mm a";
    /**
     * Get the day of the week
     * @param date - {@link Date} for which the day to be calculated
     * @return - index representing the day of the week starting with 1 for Sunday, 2 for Monday and so on
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get the display format
     * @param index - Segment of the time frame
     * @param totalSegments - Total number of segments in the time frames (usually 8)
     * @param begin - Begining {@link Date}
     * @param close- Closing {@link Date}
     * @return - Pretty string
     */
    public static String getDisplayFormat(int index, int totalSegments, Date begin, Date close) {
        long diff = close.getTime() - begin.getTime();
        diff = diff / totalSegments;
        Date start = new Date(begin.getTime() + ((index - 1) * diff));
        Date end = new Date(begin.getTime() + (index * diff));
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_FORMAT);
        sb.append(sdf.format(start));
        sb.append(" - ");
        sb.append(sdf.format(end));
        return sb.toString();
    }
}
