package io.caly.calyandroid.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import io.caly.calyandroid.Activity.EventListActivity;
import io.caly.calyandroid.Activity.SplashActivity;
import io.caly.calyandroid.R;

/**
 * Copyright 2017 JSpiner. All rights reserved.
 *
 * @author jspiner (jspiner@naver.com)
 * @project CalyAndroid
 * @since 17. 2. 13
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    //로그에 쓰일 tag
    private static final String TAG = FirebaseMessagingService.class.getSimpleName();

    public static final String INTENT_ACTION_SYNC_COMPLETE = "INTENT_ACTION_SYNC_COMPLETE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,remoteMessage.getData().toString());
        Log.d(TAG, "push received!");
        sendNotification(remoteMessage.getData().get("message"));
    }

    // TODO : 서버와 object 규격을 맞춰서 정리하기
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Push Test")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        sendMessageToActivity("hello", INTENT_ACTION_SYNC_COMPLETE);
    }

    void sendMessageToActivity(String message, String action){
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("message", message);
        sendBroadcast(intent);
    }

}
