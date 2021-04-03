package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.List;
import java.util.jar.Attributes;

public class NewAlarm extends AppCompatActivity {

    NumberPicker npHours;
    NumberPicker npMinutes;

    Spinner spnNewAlarmMedicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_alarm);

        npHours = findViewById(R.id.npHours);
        npHours.setMinValue(0);
        npHours.setMaxValue(23);

        npMinutes = findViewById(R.id.npMinutes);
        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        spnNewAlarmMedicationList = findViewById(R.id.spnNewAlarmMedicationList);

        NameList nameList = NameList.getInstance(getApplicationContext());
        List<String> names = nameList.getNames(getApplicationContext());

        String[] spinnerArray = new String[names.size()];

        int i = 0;
        for(String s : names) {
            spinnerArray[i] = s;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (NewAlarm.this, android.R.layout.simple_list_item_1, spinnerArray);
        spnNewAlarmMedicationList.setAdapter(adapter);


    }
}
