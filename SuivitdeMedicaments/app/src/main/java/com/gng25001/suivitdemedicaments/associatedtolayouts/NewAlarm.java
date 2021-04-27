package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.Alarm;
import com.gng25001.suivitdemedicaments.AlarmReceiver;
import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AlarmDAO;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.Calendar;
import java.util.List;

public class NewAlarm extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    private Button btnCancel;
    private Button btnSave;
    private NumberPicker npHours;
    private NumberPicker npMinutes;
    private Spinner spnNewAlarmMedicationList;
    private ToggleButton[] days;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    /**
     * onCreate of the new_alarm layout
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_alarm);

        //get the buttons
        btnCancel = findViewById(R.id.btnNewAlarmCancel);
        btnSave = findViewById(R.id.btnNewAlarmSave);

        //set the hours number picker
        npHours = findViewById(R.id.npHours);
        npHours.setMinValue(0);
        npHours.setMaxValue(23);

        //set the minutes number picker
        npMinutes = findViewById(R.id.npMinutes);
        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        //get the spinner
        spnNewAlarmMedicationList = findViewById(R.id.spnNewAlarmMedicationList);

        //get the list of all the medication names
        NameList nameList = NameList.getInstance(this);
        List<String> names = nameList.getNames(this);

        //create an array with the list
        String[] spinnerArray = new String[names.size()];
        int i = 0;
        for(String s : names) {
            if (s != null) {
                spinnerArray[i] = s;
                i++;
            }
        }

        //create an array adapter with the array and adds it to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (NewAlarm.this, android.R.layout.simple_list_item_1, spinnerArray);
        spnNewAlarmMedicationList.setAdapter(adapter);

        days = new ToggleButton[] {
                findViewById(R.id.tbtnSun),
                findViewById(R.id.tbtnMon),
                findViewById(R.id.tbtnTue),
                findViewById(R.id.tbtnWed),
                findViewById(R.id.tbtnThu),
                findViewById(R.id.tbtnFri),
                findViewById(R.id.tbtnSat)
        };
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * onClick Methods for the elements on the new alarm layout
     * @param view the element that was clicked
     */
    public void onClickNewAlarm(View view) {
        if (view == btnCancel) {
            //ends the activity
            finish();
        }
        else if (view == btnSave) {
            //check if at least one of the day button is selected
            if (days[0].isChecked() || days[1].isChecked() || days[2].isChecked() ||
                    days[3].isChecked() || days[4].isChecked() || days[5].isChecked()
                    || days[6].isChecked()) {

                //get all the values
                short hour = (short) npHours.getValue();  //can be casted since values are defined between 0 and 23
                short minute = (short) npMinutes.getValue(); //can be casted since values are defined between 0 and 59
                boolean[] daysArray = new boolean[] {
                        days[0].isChecked(),
                        days[1].isChecked(),
                        days[2].isChecked(),
                        days[3].isChecked(),
                        days[4].isChecked(),
                        days[5].isChecked(),
                        days[6].isChecked()
                };
                String selectedMedication = (String) spnNewAlarmMedicationList.getSelectedItem();
                //can be casted since elements are defined as Strings

                //Get the database to save the data related to the alarm
                AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                AlarmDAO alarmDAO = appDatabase.alarmDAO();

                List<Alarm> alarmList = alarmDAO.loadAllAlarms();
                int notificationID = alarmList.size();

                //saves the alarm
                Alarm alarm = new Alarm(hour, minute, selectedMedication, notificationID);
                alarmDAO.insertNewAlarm(alarm);

                //Create the alarm
                createAlarm(hour, minute, daysArray,selectedMedication,notificationID);

                //Send a confirmation message and ends the new alarm layout
                Toast.makeText(this, "Alarm Created", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                //TODO
            }
        }
    }
    //END OF PUBLIC METHOD
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHOD
    /**
     * Set the alarm with the newly created data
     * @param hour the hour the alarm must trigger
     * @param minute the minute the alarm must trigger
     * @param daysArray the days the alarm must trigger
     * @param medicationName the name of the medication associated
     * @param notificationID the ID associated to the pending intent
     */
    private void createAlarm(short hour, short minute, boolean[] daysArray, String medicationName, int notificationID) {

        //Create the indent and adds the necessary data
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyIntent.putExtra("notificationID", notificationID);
        notifyIntent.putExtra("medicationName", medicationName);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(
            this, notificationID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Get the time from the internal calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, (minute - 1));

        //Build the alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                alarmPendingIntent);
    }
}
