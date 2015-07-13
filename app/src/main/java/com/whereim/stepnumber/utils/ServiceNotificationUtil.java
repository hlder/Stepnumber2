package com.whereim.stepnumber.utils;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.whereim.stepnumber.R;

/**
 * Created by HLD on 2015/7/10.
 */
public class ServiceNotificationUtil {
    private final int ID_SERVICE_FOREGROUND=30491;
    private RemoteViews remoteViews=null;
    private NotificationCompat.Builder mBuilder;

    public void serviceStartForeground(Service service,Intent intent,String title,String content){
        mBuilder=new NotificationCompat.Builder(service);
        PendingIntent pendingIntent= PendingIntent.getActivity(service,0,intent,0);
        mBuilder.setContentIntent(pendingIntent);
        remoteViews=new RemoteViews(service.getPackageName(), R.layout.view_notifi);
        remoteViews.setCharSequence(R.id.textView,"setText",title);
        remoteViews.setCharSequence(R.id.textView2, "setText", content);
        mBuilder.setContent(remoteViews);
        mBuilder.setSmallIcon(R.mipmap.icon);
        service.startForeground(ID_SERVICE_FOREGROUND, mBuilder.build());
    }
    public void updateNotifiText(String title,String content){
        if(remoteViews==null){
            return;
        }
        remoteViews.setCharSequence(R.id.textView,"setText",title);
        remoteViews.setCharSequence(R.id.textView2,"setText",content);
        mBuilder.setContent(remoteViews);
    }


    public void serviceStopForeground(Service service){
        service.stopForeground(true);
    }



}
