package com.tr.muhurath.app.muhurat.kaal;

import com.tr.muhurath.app.muhurat.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bkatika on 1/31/16.
 */
public class GuliKaal implements Kaal {
    @Override
    public int getMuhurat(int dayOfWeek) {
        switch (dayOfWeek) {
            case 0:
                return 7;
            case 1:
                return 6;
            case 2:
                return 5;
            case 3:
                return 4;
            case 4:
                return 3;
            case 5:
                return 2;
            case 6:
                return 1;
            default:
                //Assuming Sunday
                return 7;
        }
    }

    @Override
    public String getMuhuratForDisplay(Date sunRise, Date sunSet) {
        int muhurat = getMuhurat(DateUtils.getDayOfWeek(sunRise));
        return DateUtils.getDisplayFormat(muhurat, TOTAL_SEGMENTS_IN_DAY, sunRise, sunSet);
    }
}
