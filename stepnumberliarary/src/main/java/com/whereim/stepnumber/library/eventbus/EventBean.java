package com.whereim.stepnumber.library.eventbus;

/**
 * Created by HLD on 2015/7/16.
 */
public class EventBean {
    private int eventType;
    private String eventMsg;
    private Object eventObj;
    public int getEventType() {
        return eventType;
    }
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
    public String getEventMsg() {
        return eventMsg;
    }
    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
    }
    public Object getEventObj() {
        return eventObj;
    }
    public void setEventObj(Object eventObj) {
        this.eventObj = eventObj;
    }
}
