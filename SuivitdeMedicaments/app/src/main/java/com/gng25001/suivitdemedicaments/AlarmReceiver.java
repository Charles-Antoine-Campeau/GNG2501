package com.gng25001.suivitdemedicaments;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;

    /**
     * Called when the BroadcastReceiver receives an Intent broadcast
     * @param context context in which the receiver is running
     * @param intent intent being received
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification(context);
    }

    private void deliverNotification (Context context) {
            //TODO
    }
}