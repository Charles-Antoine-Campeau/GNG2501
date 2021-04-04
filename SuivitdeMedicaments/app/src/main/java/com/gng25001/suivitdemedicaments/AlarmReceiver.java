package com.gng25001.suivitdemedicaments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.gng25001.suivitdemedicaments.associatedtolayouts.NotificationMessage;

public class AlarmReceiver extends BroadcastReceiver {

    //**********************************************************************************************
    //VARIABLES
    private NotificationManager mNotificationManager;

    private final String MEDICATION_TO_TAKE = "MEDICATION_TO_TAKE";
    private final String TO_DO_LATER = "TO_DO_LATER";
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONRECEIVE
    /**
     * Called when the BroadcastReceiver receives an Intent broadcast
     * @param context context in which the receiver is running
     * @param intent intent being received
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification (
                context,
                intent.getIntExtra("notificationID", 0),
                intent.getStringExtra("medicationName")
        );
    }
    //END OF ONRECEIVE
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Deliver the notification associated to the alarm
     * @param context context in which the receiver is running
     * @param notificationID id associated to the alarm and notification
     * @param medicationName the medication for which the alarm is running
     */
    private void deliverNotification (Context context, int notificationID, String medicationName) {
        //Intent for the layout when the notification is clicked
        Intent contentIntent = new Intent(context, NotificationMessage.class);
        contentIntent.putExtra("medicationName", medicationName);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                context, notificationID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Creates the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MEDICATION_TO_TAKE)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Medication to take")
                .setContentText(medicationName)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //sends the notification
        mNotificationManager.notify(notificationID, builder.build());
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************
}