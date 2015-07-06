package com.whereim.stepnumber.manager;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.whereim.stepnumber.params.AppParams;

/**
 * Created by HLD on 2015/7/6.
 */
public class DbManager {
    private static DbUtils db;
    private static DbUtils  getInstance(Context context){
        if(db==null){
            db=DbUtils.create(context, AppParams.DB_NAME, AppParams.DB_VERSION, new DbUtils.DbUpgradeListener() {
                /**
                 * 数据库升级
                 * @param dbUtils
                 * @param i
                 * @param i1
                 */
                @Override
                public void onUpgrade(DbUtils dbUtils, int i, int i1) {

                }
            });
        }
        return db;
    }
    /**
     * 保存数据
     * @param bean
     */
    public static boolean save(Context context,Object bean){
        try {
            getInstance(context).save(bean);
            return true;
        } catch (DbException e) {
        }
        return false;
    }

    /**
     * 删除数据
     * @param context
     * @param bean
     * @return
     */
    public static boolean delete(Context context,Object bean){
        try {
            getInstance(context).delete(bean);
            return true;
        } catch (DbException e) {
        }
        return false;
    }

}
