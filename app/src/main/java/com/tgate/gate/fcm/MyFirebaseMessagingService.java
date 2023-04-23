package com.tgate.gate.fcm;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.tgate.gate.R;
import com.tgate.gate.api.DataResponseListener;
import com.tgate.gate.util.PrefsManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage( remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


    public void getFirebaseMessage(String title, String msg){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"T_Gate")
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(101, builder.build());
    }
}

