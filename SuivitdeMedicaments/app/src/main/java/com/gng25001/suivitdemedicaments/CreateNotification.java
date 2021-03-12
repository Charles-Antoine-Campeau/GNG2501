package com.gng25001.suivitdemedicaments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.gng25001.suivitdemedicaments.associatedtolayouts.NotificationMessage;

public class CreateNotification {

    //**********************************************************************************************
    //VARIABLES
    private static CreateNotification instance;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTOR

    /**
     * Constructor of the class
     */
    private CreateNotification() {
        instance = this;
    }
    //END OF CONSTRUCTOR
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * Get the singleton instance of the class
     * @return the instance of the class
     */
    public CreateNotification getInstance() {
        if (instance == null) {
            new CreateNotification();
        }
        return instance;
    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Create a high priority notification
     * @param appContext the application context
     * @param CHANNEL_ID ID of the channel in which the notification must be place
     * @param title title of the notification
     * @param text text of the notification
     * @param category predefined category of the notification
     * @return a notification created with the given parameters
     */
    private Notification highNotificationBuilder(
            Context appContext, String CHANNEL_ID, String title, String text, String category){

        //create the intent to launch for the full screen notification
        Intent notificationMessageIntent = new Intent(appContext, NotificationMessage.class);

        //create the TaskStackBuilder and add the intent, which inflates the back stack
        // (whatever what that means)
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(appContext);
        stackBuilder.addNextIntentWithParentStack(notificationMessageIntent);

        //get the PendingIntent containing the entire back stack
        PendingIntent notificationMessagePendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder highPriorityBuilder =
                new NotificationCompat.Builder(appContext, CHANNEL_ID)
                .setFullScreenIntent(notificationMessagePendingIntent, true)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(category)
                ;

        return highPriorityBuilder.build();
    }

    /**
     * Create a default priority notification
     * @param appContext the application context
     * @param CHANNEL_ID ID of the channel in which the notification must be place
     * @param title title of the notification
     * @param text text of the notification
     * @param category predefined category of the notification
     * @return a notification created with the given parameters
     */
    private Notification defaultNotificationBuilder(Context appContext, String CHANNEL_ID,
                                                    String title, String text, String category) {
        NotificationCompat.Builder defaultPriorityBuilder =
                new NotificationCompat.Builder(appContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(category)
                ;

        return defaultPriorityBuilder.build();
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************
}










