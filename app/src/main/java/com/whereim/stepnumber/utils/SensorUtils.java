package com.whereim.stepnumber.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.whereim.stepnumber.bean.RecordBean;
import com.whereim.stepnumber.manager.DbManager;
import com.whereim.stepnumber.params.AppParams;

import java.util.Date;

/**
 * ������
 * Created by HLD on 2015/7/3.
 */
public class SensorUtils {
    private Context context;
    private SensorManager sensorManager;
    private OnStepListener onStepListener;

    private boolean isStep=true;//�Ƿ��¼����

    public SensorUtils(Context context,OnStepListener onStepListener) {
        this.context=context;
        this.onStepListener=onStepListener;
        init();
    }
    public SensorUtils(Context context) {
        this.context=context;
        init();
    }
    private void init(){
        sensorManager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                isAStep(context, event);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.e("dddd","onAccuracyChanged:"+accuracy);
            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * �ж��Ƿ�������һ��
     */
    public  void isAStep(Context context,SensorEvent event){
        int value = SpManager.getInt(context, AppParams.SP_KEY_SENSOR_VALUE,13);//ҡһҡ��ֵ,��ͬ�ֻ��ܴﵽ�����ֵ��ͬ,��ĳƷ���ֻ�ֻ�ܴﵽ20
        //values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��
        float[] values = event.values;
        int x = (int) values[0];
        int y = (int) values[1];
        int z = (int) values[2];
        int mul=(x*x+y*y+z*z);
        if(mul<maxSize&&mul>minSize){//��ʾ����ͷ�ˣ���ʼ��һ��
            if(mul>(value*value)) {
                Log.d("dddd", "" + mul);
                if(onStepListener!=null){
                    onStepListener.onStep(mul);
                }
            }
            initSize();
        }
        if(minSize>mul){
            minSize=mul;
        }
        if(maxSize<mul){
            maxSize=mul;
        }
    }
    private int minSize=10000000;
    private int maxSize=0;
    private void initSize(){
        minSize=10000000;
        maxSize=0;
    }

    public void setOnStepListener(OnStepListener onStepListener) {
        this.onStepListener = onStepListener;
    }

    public interface OnStepListener{
        public void onStep(int rage);
    }


}
