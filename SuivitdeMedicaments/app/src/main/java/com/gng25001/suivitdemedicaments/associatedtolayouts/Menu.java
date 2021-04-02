package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;

public class Menu extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    //Elements of the layout
    private Button btnMedList;
    private Button btnSettings;
    private Button btnAlarmList;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Set the layout as the menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        getLayoutElements();

        createNotificationChannels();
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * Function called by every button on the menu activity
     * @param view the item that was clicked
     */
    public void onClick(View view){

        //Determine which button was press
        if (view == btnMedList) {
            //Open the medicament list layout and class
            startActivity(new Intent(getApplicationContext(), MedicamentList.class));
        } else if (view == btnSettings) {
            //Open the settings layout and class
            startActivity(new Intent(getApplicationContext(), Settings.class));
        } else if (view == btnAlarmList) {
            //Open the alarm list layout and class
            startActivity(new Intent(getApplicationContext(), AlarmList.class));
        }

    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Get the elements from the layout
     */
    private void getLayoutElements(){
        btnMedList = findViewById(R.id.btnMenuGoToMedList);
        btnSettings = findViewById(R.id.btnMenuSettings);
        btnAlarmList = findViewById(R.id.btnMenuAlarmList);
    }

    /**
     * Creates the notification channels for the app
     */
    private void createNotificationChannels() {
        //create notification if the SDK is Oreo or newer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //create a notification channel for elements that needs immediate attention
            NotificationChannel importantChannel = new NotificationChannel(
                    "MEDICATION_TO_TAKE",
                    "To do now",
                    NotificationManager.IMPORTANCE_HIGH
            );

            //create a notification channel for elements which can be dealt with at later time
            NotificationChannel lesserChannel = new NotificationChannel(
                    "TO_DO_LATER",
                    "Notice",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            // Register the channels with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(importantChannel);
            notificationManager.createNotificationChannel(lesserChannel);
        }
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************


}