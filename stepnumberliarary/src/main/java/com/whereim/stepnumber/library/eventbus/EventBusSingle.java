package com.whereim.stepnumber.library.eventbus;

/**
 * Created by HLD on 2015/7/16.
 */
public class EventBusSingle {
    private static EventBus eventBus;
    public static synchronized EventBus getInstance(){
        if(eventBus==null){
            eventBus=new EventBus();
        }
        return eventBus;
    }
    public static void register(EventListener listener){
        getInstance().register(listener);
    }
    public static void unregister(EventListener listener){
        getInstance().unregister(listener);
    }
    public static void sendEvent(EventBean obj){
        getInstance().sendEvent(obj);
    }

}
