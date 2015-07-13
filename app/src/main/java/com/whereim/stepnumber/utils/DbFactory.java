package com.whereim.stepnumber.utils;

import android.content.Context;
import android.database.Cursor;

import com.lidroid.xutils.exception.DbException;
import com.whereim.stepnumber.manager.DbManager;
import com.whereim.stepnumber.params.AppParams;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by HLD on 2015/7/10.
 */
public class DbFactory {



    public static long queryStepCount(Context context){
        Calendar calendar=Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
        String sql="select count(*) from "+ AppParams.TABLE_NAME_RECORD+" where time>"+calendar.getTimeInMillis();
        try {
            Cursor c = DbManager.execQuery(context,sql);
            if(c.moveToNext()){
                String count=c.getString(c.getColumnIndex("count(*)"));
                try{
                    return Long.parseLong(count);
                }catch (NumberFormatException e){}
            }
            c.close();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
