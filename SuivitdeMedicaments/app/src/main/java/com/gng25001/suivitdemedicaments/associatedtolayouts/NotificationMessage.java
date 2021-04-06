package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.gng25001.suivitdemedicaments.Alarm;
import com.gng25001.suivitdemedicaments.AlarmReceiver;
import com.gng25001.suivitdemedicaments.Medicament;
import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AlarmDAO;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.MedicamentDAO;

import java.util.Calendar;

public class NotificationMessage extends AppCompatActivity {
    //**********************************************************************************************
    //VARIABLES
    private Button btnNMTakeDose;

    private MedicamentDAO medicamentDAO;
    private Medicament medicament;
    private AlarmDAO alarmDAO;
    private Alarm alarm;
    private int notificationID;

    private final String MEDICATION_TO_TAKE = "MEDICATION_TO_TAKE";
    private final String TO_DO_LATER = "TO_DO_LATER";
    //END OF VARIABLES
    //**********************************************************************************************

    @Override
    public void onCreate(Bundle savedBundleState) {
        super.onCreate(savedBundleState);
        setContentView(R.layout.notification_message);

        //get the necessary views
        TextView txtMNMedicationName = findViewById(R.id.txtNMMedicationName);
        TextView txtNMQuantityLeft = findViewById(R.id.txtNMQuantityLeft);
        TextView txtNMDosesSize = findViewById(R.id.txtNMDosesSize);
        btnNMTakeDose = findViewById(R.id.btnNMTakeDose);

        //get the intent and its content from the alarm
        Intent intent = getIntent();
        notificationID = intent.getIntExtra("notificationID", -1);
        String name = intent.getStringExtra("medicationName");

        //get the related medication and alarm from the database
        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        medicamentDAO = appDatabase.medicamentDAO();
        medicament = medicamentDAO.loadMediament(name);
        alarmDAO = appDatabase.alarmDAO();
        alarm = alarmDAO.loadAlarm(notificationID);

        //set the text views with the related content
        txtMNMedicationName.setText(name);
        txtNMDosesSize.setText(getString(R.string.quantity_to_take) + medicament.getDose());
        txtNMQuantityLeft.setText(getString(R.string.quantity_left) + medicament.getTotalOfMedicament());
    }

    /**
     * on click method for the notification message layout
     * @param view th element that was clicked
     */
    public void OnClickMN(View view) {
        if (view == btnNMTakeDose) {
            //reduce the total of medication
            medicament.setTotalOfMedicament(medicament.getTotalOfMedicament() - medicament.getDose());
            medicamentDAO.updateMedicament(medicament);

            //if the total is less than a threshold, notify the user to order new medication
            if (medicament.getTotalOfMedicament() <
                    medicament.getDose() * medicament.getWaitingTimeBeforeNewStockDays() * 2) {
                //makes the notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, TO_DO_LATER)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Medication to order")
                        .setContentText("You need to order: " + medicament.getName())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                //sends the notification
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(1000, builder.build());
            }

            createAlarm(alarm.getHour(), alarm.getMinute(), alarm.getMedicationName(), alarm.getNotificationID());

            Toast.makeText(this, "Medication Taken", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * set the exact alarm for the next day
     * @param hour the hour to trigger the alarm
     * @param minute the minute to trigger the alarm
     * @param medicationName the name of the associated medication
     * @param notificationID the id of the alarm and associated notification
     */
    private void createAlarm(short hour, short minute, String medicationName, int notificationID) {

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
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, (minute - 1));

        //Build the alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                alarmPendingIntent);
    }

}
