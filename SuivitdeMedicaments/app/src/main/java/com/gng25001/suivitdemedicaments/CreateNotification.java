package com.gng25001.suivitdemedicaments;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.gng25001.suivitdemedicaments.associatedtolayouts.NotificationMessage;

/**
 * Class called whenever there is a need to create a notification.  Implemented as singleton with
 * getInstance for the instance.  Create and return a high or default priority notification with the
 * given parameters, or throws an IllegalArgumentException if any of the parameters are invalid.
 * @author Charles-Antoine
 * 12/03/2021
 */
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

    /**
     * Public method to create notification within the application
     * @param appContext the application context
     * @param CHANNEL_ID ID of the channel in which the notification must be place
     * @param title title of the notification
     * @param text text of the notification
     * @param category predefined category of the notification
     * @param highPriority the priority of the notification
     * @return a notification created with the given parameters
     * @throws IllegalArgumentException if any parameters are not valid
     */
    public Notification buildNotification(Context appContext, String CHANNEL_ID, String title,
                                          String text, String category, boolean highPriority)
            throws IllegalArgumentException {

        //determine if the channel id is valid
        if (CHANNEL_ID.equals("MEDICATION_TO_TAKE") || CHANNEL_ID.equals("TO_DO_LATER")) {

            //determine if the notification category is valid
            if (category.equals(Notification.CATEGORY_ALARM) || category.equals(Notification.CATEGORY_CALL)
            || category.equals(Notification.CATEGORY_EMAIL) || category.equals(Notification.CATEGORY_ERROR)
            || category.equals(Notification.CATEGORY_EVENT) || category.equals(Notification.CATEGORY_MESSAGE)
            || category.equals(Notification.CATEGORY_NAVIGATION) || category.equals(Notification.CATEGORY_PROGRESS)
            || category.equals(Notification.CATEGORY_PROMO) || category.equals(Notification.CATEGORY_RECOMMENDATION)
            || category.equals(Notification.CATEGORY_REMINDER) || category.equals(Notification.CATEGORY_SERVICE)
            || category.equals(Notification.CATEGORY_SOCIAL) || category.equals(Notification.CATEGORY_STATUS)
            || category.equals(Notification.CATEGORY_SYSTEM) || category.equals(Notification.CATEGORY_TRANSPORT) ) {

                //makes sure there is a title
                if (title.isEmpty()) {
                    throw new IllegalArgumentException("No title");
                }

                //make sure there is a text
                if (text.isEmpty()) {
                    throw new IllegalArgumentException("No text");
                }
            }
            else {
                throw new IllegalArgumentException("Invalid category");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid CHANNEL_ID");
        }

        //return a notification based on the priority
        if (highPriority) {
            return highNotificationBuilder(appContext, CHANNEL_ID, title, text, category);
        }
        else {
            return defaultNotificationBuilder(appContext, CHANNEL_ID, title, text, category);
        }
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










