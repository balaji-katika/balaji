package com.tr.muhurath.app.muhurat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import com.tr.muhurath.app.muhurat.kaal.GuliKaal;
import com.tr.muhurath.app.muhurat.kaal.Kaal;
import com.tr.muhurath.app.muhurat.kaal.RahuKaal;
import com.tr.muhurath.app.muhurat.kaal.YamaGandaKaal;
import com.tr.muhurath.app.muhurat.utils.DateUtils;
import com.tr.muhurath.app.muhurat.utils.SunRiseSetUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Activity displaying the Muhurat Summary
 * Created by Balaji Katika (balaji.katika@gmail.com) on 1/30/16.
 */
public class MuhuratSummaryActivity extends AppCompatActivity {
    public static final String LBL_DATE_DISPLAY = "E dd-MMM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhurat_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Parse the date passed to the activity
        Intent intent = getIntent();
        String inputDate = intent.getStringExtra(IntentConstants.DATE_DDMMYYYY);
        Date selectedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            selectedDate = sdf.parse(inputDate);
        }
        catch (ParseException parseException) {
            //Set the current date
            selectedDate = new Date();
        }

        //Calculate Sunrise and SunSet
        //Date sunRise= SunRiseSetUtil.getSunRise(selectedDate);
        //Date sunSet=SunRiseSetUtil.getSunSet(selectedDate);
        Location loc = new Location(AppConfiguration.latitude, AppConfiguration.longitude);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(loc, TimeZone.getDefault().getID());
        Date sunRise = SunRiseSetUtil.getSunRiseLocationBased(selectedDate, calculator);
        Date sunSet = SunRiseSetUtil.getSunSetLocationBased(selectedDate, calculator);

        //Generate label with the selected date
        SimpleDateFormat displayDateFormat = new SimpleDateFormat(LBL_DATE_DISPLAY);
        TextView dateHolder = (TextView) findViewById(R.id.txtMuhuratDateHolder);
        dateHolder.setText("Muhurat for " + displayDateFormat.format(selectedDate));

        Kaal kaal = null;
        TextView kalHolder = null;

        //Display Guli Kaal
        kaal = new GuliKaal();
        kalHolder = (TextView) findViewById(R.id.txtMuhuratGuli);
        kalHolder.setText("Gulika : " + kaal.getMuhuratForDisplay(sunRise, sunSet));

        //Display Rahu Kaal
        kaal = new RahuKaal();
        kalHolder = (TextView) findViewById(R.id.txtMuhuratRahu);
        kalHolder.setText("Rahu Kaalam : " + kaal.getMuhuratForDisplay(sunRise, sunSet));

        //Display Yama Kaal
        kaal = new YamaGandaKaal();
        kalHolder = (TextView) findViewById(R.id.txtMuhuratYama);
        kalHolder.setText("YamaGandam : " + kaal.getMuhuratForDisplay(sunRise, sunSet));

        kalHolder = (TextView) findViewById(R.id.txtMuhuraShoolam);
        kalHolder.setText("Shoolam : " + DateUtils.getShoolamDirection(selectedDate));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        //Display Sun Rise
        kalHolder = (TextView) findViewById(R.id.txtMuhuraSunRise);
        kalHolder.setText("Sun Rise : " + calculator.getOfficialSunriseForDate(calendar));

        //Display Sun Set
        kalHolder = (TextView) findViewById(R.id.txtMuhuraSunSet);
        kalHolder.setText("Sun Set : " + calculator.getOfficialSunsetForDate(calendar));

        //Set back button on Tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
