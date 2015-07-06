package com.whereim.stepnumber.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.whereim.stepnumber.params.AppParams;

/**
 * Created by HLD on 2015/7/3.
 */
public class SpManager {
    public static void saveInt(Context context,String key,int value){
        getSharedPreferences(context).edit().putInt(key,value).commit();
    }
    public static void saveString(Context context,String key,String value){
        getSharedPreferences(context).edit().putString(key, value).commit();
    }
    public static String getString(Context context,String key){
        return getSharedPreferences(context).getString(key,"");
    }
    public static int getInt(Context context,String key){
        return getSharedPreferences(context).getInt(key, 0);
    }
    public static int getInt(Context context,String key,int def){
        return getSharedPreferences(context).getInt(key,def);
    }

//    public static void save


    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(AppParams.SHAREPREFERENCES_BASE_NAME,AppParams.SHAREPREFERENCES_BASE_MODE);
    }
}
