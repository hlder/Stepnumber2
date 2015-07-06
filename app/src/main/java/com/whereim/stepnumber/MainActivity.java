package com.whereim.stepnumber;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.whereim.stepnumber.bean.RecordBean;
import com.whereim.stepnumber.manager.DbManager;
import com.whereim.stepnumber.utils.SensorUtils;

import java.util.Date;


public class MainActivity extends Activity {
    private SensorManager sensorManager;
    private final String TAG="dddd";

    private boolean isStep=true;//�Ƿ��¼����


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepare();


    }
    private void prepare(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                boolean flag = SensorUtils.isAStep(MainActivity.this, event);//����true��ʾ����һ��
                if (isStep&&flag) {//��Ҫ��¼����(������һ)
                    isStep = false;
                } else if(isStep&&!flag){//����Ҫ��¼��������ʾ�Ѿ���¼�ˣ������ݽ�����
                    isStep = true;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
