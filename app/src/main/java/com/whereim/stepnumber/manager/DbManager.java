package com.whereim.stepnumber.manager;

import android.content.Context;
import android.database.Cursor;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
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
     * ��������
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
     * ɾ������
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

    public static Object findFrist(Context context,Selector selector){
        try {
            return getInstance(context).findFirst(selector);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cursor execQuery(Context context,String sql) throws DbException {
        return getInstance(context).execQuery(sql);
    }

}
