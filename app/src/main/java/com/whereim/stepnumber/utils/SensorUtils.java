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
     * 判断是否算走了一步
     */
    public static boolean isAStep(Context context,SensorEvent event){
        int value=SpManager.getInt(context, AppParams.SP_KEY_SENSOR_VALUE,15);//摇一摇阀值,不同手机能达到的最大值不同,如某品牌手机只能达到20
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        int x = (int) values[0];
        int y = (int) values[1];
        int z = (int) values[2];

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if((x*x+y*y+z*z)>(value*value)){//如果正方体的对角线，大于预设值，表示成功了
                return true;
            }
        }
        return false;
    }
}
