package com.example.biruk.androidclientchat.Notification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("Test activity");
        setContentView(text);

        NotificationHandler notificationHandler = NotificationHandler.getInstance(this);
//        notificationHandler.createSimpleNotification(this);

    }
}