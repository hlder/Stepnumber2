package com.whereim.stepnumber.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.whereim.stepnumber.params.AppParams;

/**
 * Created by HLD on 2015/7/3.
 */
public class SensorUtils {
    /**
     * �ж��Ƿ�������һ��
     */
    public static boolean isAStep(Context context,SensorEvent event){
        int value=SpManager.getInt(context, AppParams.SP_KEY_SENSOR_VALUE,15);//ҡһҡ��ֵ,��ͬ�ֻ��ܴﵽ�����ֵ��ͬ,��ĳƷ���ֻ�ֻ�ܴﵽ20
        int sensorType = event.sensor.getType();
        //values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��
        float[] values = event.values;
        int x = (int) values[0];
        int y = (int) values[1];
        int z = (int) values[2];

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if((x*x+y*y+z*z)>(value*value)){//���������ĶԽ��ߣ�����Ԥ��ֵ����ʾ�ɹ���
                return true;
            }
        }
        return false;
    }
}
