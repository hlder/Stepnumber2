package com.whereim.stepnumber.bean;

import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Id;
import com.whereim.stepnumber.params.AppParams;

/**
 * Created by HLD on 2015/7/6.
 */
@Table(name = AppParams.TABLE_NAME_RECORD)
public class RecordBean {
    /**id*/
    @Id(column = "id")
    private int id;
    private long time;
    private int rage;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getRage() {
        return rage;
    }
    public void setRage(int rage) {
        this.rage = rage;
    }
}
