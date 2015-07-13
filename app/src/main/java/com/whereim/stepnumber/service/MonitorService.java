package com.whereim.stepnumber.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lidroid.xutils.db.sqlite.Selector;
import com.whereim.stepnumber.MainActivity;
import com.whereim.stepnumber.R;
import com.whereim.stepnumber.bean.RecordBean;
import com.whereim.stepnumber.manager.DbManager;
import com.whereim.stepnumber.params.AppParams;
import com.whereim.stepnumber.utils.DbFactory;
import com.whereim.stepnumber.utils.SensorUtils;
import com.whereim.stepnumber.utils.ServiceNotificationUtil;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by HLD on 2015/7/9.
 */
public class MonitorService extends Service {
    private SensorUtils sensorUtil;
    private Executor pool= Executors.newSingleThreadExecutor();
    private ServiceNotificationUtil notificationUtil;
    long count;
    @Override
    public void onCreate() {
        super.onCreate();
        prePare();

         count = DbFactory.queryStepCount(this);
        Intent intent=new Intent(this, MainActivity.class);//挂载的intent
        notificationUtil=new ServiceNotificationUtil();
        notificationUtil.serviceStartForeground(this,intent,getString(R.string.notification_title_today)+""+count+getString(R.string.notification_title_step),""+getString(R.string.notification_content));
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    Log.d("dddd","count:"+count);
                    notificationUtil.updateNotifiText(getString(R.string.notification_title_today)+""+(count++)+getString(R.string.notification_title_step),""+getString(R.string.notification_content));
                }
            }
        }.start();
    }
    private void prePare(){
        sensorUtil=new SensorUtils(this, new SensorUtils.OnStepListener() {
            @Override
            public void onStep(int rage) {
                pool.execute(new MyRunnable(rage));
            }
        });
    }
    private class MyRunnable implements Runnable{
        int rage;
        public MyRunnable(int rage) {
            this.rage=rage;
        }
        @Override
        public void run() {
            long time=new Date().getTime();
            RecordBean lastBean= (RecordBean) DbManager.findFrist(MonitorService.this, Selector.from(RecordBean.class).orderBy("id",true));
            if(lastBean==null||Math.abs(lastBean.getTime() - time)> AppParams.stepGapTime){//间隔大于1秒再记录
                RecordBean bean=new RecordBean();
                bean.setRage(rage);
                bean.setTime(new Date().getTime());
                DbManager.save(MonitorService.this, bean);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationUtil.serviceStopForeground(this);
    }
}
