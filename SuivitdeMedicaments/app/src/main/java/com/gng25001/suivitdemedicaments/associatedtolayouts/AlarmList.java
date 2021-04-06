package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.Alarm;
import com.gng25001.suivitdemedicaments.AlarmReceiver;
import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AlarmDAO;
import com.gng25001.suivitdemedicaments.database.AppDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class AlarmList extends AppCompatActivity {
    //**********************************************************************************************
    //VARIABLES
    private Button btnNewAlarm;
    private LinearLayout llAlarmList;

    private List<Alarm> alarms;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);

        btnNewAlarm = findViewById(R.id.btnNewAlarm);
        llAlarmList = findViewById(R.id.llAlarmList);

        getAlarms();
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * OnClick method for the Alarm list layout
     * @param view the element that was clicked
     */
    public void onClickAlarmList(View view) {
        if (view == btnNewAlarm) {
            startActivity(new Intent(getApplicationContext(), NewAlarm.class));
        }
    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PROTECTED METHODS
    @Override
    protected void onRestart() {
        super.onRestart();

        getAlarms();
    }
    //END OF PROTECTED METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Gets the list of the alarms saved in the database
     */
    private void getAlarms() {
        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        AlarmDAO alarmDAO = appDatabase.alarmDAO();
        alarms = alarmDAO.loadAllAlarms();

        setList();
    }

    /**
     * Add the important content of the alarms to the layout
     */
    private void setList() {
        //the layout on which everything must be placed
        LinearLayout llAlarmList = findViewById(R.id.llAlarmList);

        for (Alarm alarm : alarms) {
            //horizontal layout for each alarm
            LinearLayout container = new LinearLayout(this);
            container.setOrientation(LinearLayout.HORIZONTAL);

            //Text View for the time of the alarm
            TextView time = new TextView(this);
            String timeText = String.format("%s:%s", String.format("%02d", alarm.getHour()), String.format("%02d", alarm.getMinute()));
            time.setText(timeText);
            time.setTextSize(35);
            container.addView(time);

            //Button to delete the alarm
            Button btnDelete = new Button(this);
            btnDelete.setTextSize(35);
            btnDelete.setText(R.string.delete_alarm);
            btnDelete.setId(alarm.getNotificationID());
            btnDelete.setOnClickListener(v -> {
                //Creates an alert dialog for the user to confirm that he wants to delete the alarm
                AlertDialog.Builder builder = new AlertDialog.Builder(AlarmList.this);
                builder.setCancelable(true);
                builder.setTitle("Delete alarm?");
                builder.setPositiveButton("Confirm",
                        (dialog, which) -> {

                            Intent notifyIntent = new Intent(this, AlarmReceiver.class);
                            notifyIntent.putExtra("notificationID", alarm.getNotificationID());
                            notifyIntent.putExtra("medicationName", alarm.getMedicationName());

                            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast (
                                    this, alarm.getNotificationID(), notifyIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );

                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            alarmManager.cancel(alarmPendingIntent);

                            AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                            AlarmDAO alarmDAO = appDatabase.alarmDAO();
                            alarmDAO.deleteAlarm(alarm);

                            Toast.makeText(this, "Alarm Deleted", Toast.LENGTH_SHORT).show();

                            //reset the interface
                            startActivity(getIntent());
                            finish();
                            overridePendingTransition(0, 0);
                        }
                );
                builder.setNegativeButton(android.R.string.cancel,
                        (dialog, which) -> {/*do nothing*/}
                );
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });
            container.addView(btnDelete);

            //add the alarm layout to the main layout
            llAlarmList.addView(container);
        }
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************
}
