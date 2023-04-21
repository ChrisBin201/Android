package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    private final static String CHANNEL_ID = "Channel 1";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("ABC")) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID,"Channel 1",NotificationManager.IMPORTANCE_HIGH);
                channel1.setDescription("Mieu ta 1");
                manager.createNotificationChannel(channel1);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setContentTitle(intent.getStringExtra("name"))
                        .setContentText(intent.getStringExtra("noidung"))
                        .setSmallIcon(R.drawable.baseline_notifications)
                        .setColor(Color.BLUE)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setCategory(NotificationCompat.CATEGORY_ALARM);
                manag78ker.notify(getId(),builder.build());
            }
        }
    }

    public int getId() {
        return (int)new Date().getTime();
    }
}
