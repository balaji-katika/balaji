package com.tr.muhurath.app.muhurat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tr.muhurath.app.muhurat.kaal.GuliKaal;
import com.tr.muhurath.app.muhurat.kaal.Kaal;
import com.tr.muhurath.app.muhurat.kaal.RahuKaal;
import com.tr.muhurath.app.muhurat.utils.SunRiseSetUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MuhuratSummaryActivity extends AppCompatActivity {

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
        Date sunRise= SunRiseSetUtil.getSunRise(selectedDate);
        Date sunSet=SunRiseSetUtil.getSunSet(selectedDate);

        //Generate label with the selected date
        SimpleDateFormat displayDateFormat = new SimpleDateFormat("E yyyy.dd.MM");
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
        kaal = new GuliKaal();
        kalHolder = (TextView) findViewById(R.id.txtMuhuratYama);
        kalHolder.setText("YamaGandam : " + kaal.getMuhuratForDisplay(sunRise, sunSet));

        //Set back button on Tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
