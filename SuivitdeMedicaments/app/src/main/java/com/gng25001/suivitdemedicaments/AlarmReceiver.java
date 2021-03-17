package com.gng25001.suivitdemedicaments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gng25001.suivitdemedicaments.CreateNotification;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification(context);
    }

    private void deliverNotification (Context context) {

        CreateNotification createNotification  = CreateNotification.getInstance();


        try {
            Notification notification = createNotification.buildNotification(
                    context, "MEDICATION_TO_TAKE", "Title", "Text",
                    Notification.CATEGORY_ALARM, true);

            mNotificationManager.notify(1, notification);
        }
        catch (Exception e) {
            //TO DO
        }




    }
}