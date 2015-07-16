package com.whereim.stepnumber.manager;

import com.whereim.stepnumber.bean.EventBean;
import com.whereim.stepnumber.library.eventbus.EventBusSingle;

/**
 * Created by HLD on 2015/7/16.
 */
public class EventManager {
    public static void register(){
    }
    public static void sendEvent(EventBean event){
        EventBusSingle.sendEvent(event);
    }
    public static void sendEvent(int type,String message){
        EventBean bean=new EventBean();
        bean.setEventType(type);
        bean.setEventMsg(message);
        sendEvent(bean);
    }
    public static void sendEvent(int type,Object obj){
        EventBean bean=new EventBean();
        bean.setEventType(type);
        bean.setEventObj(obj);
        sendEvent(bean);
    }
}
