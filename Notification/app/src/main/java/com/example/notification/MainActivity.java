package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button bt;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener( view -> {
            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(MainActivity.this,
                                            MyNotification.CHANNEL_ID)
                                            .setContentTitle("lan mua hang")
                                            .setContentTitle("Mua 2 ao, 1 ao dep")
                                            .setSmallIcon(R.drawable.baseline_notifications)
                                            .setColor(Color.RED)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND)
                                            .setCategory(NotificationCompat.CATEGORY_ALARM);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
            managerCompat.notify(getId(), builder.build());

        });
    }
    private int getId() {
        return (int)new Date().getTime();
    }
}