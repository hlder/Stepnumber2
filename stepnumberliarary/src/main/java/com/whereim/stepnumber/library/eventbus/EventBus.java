package com.whereim.stepnumber.library.eventbus;

import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by HLD on 2015/7/16.
 */
public class EventBus {
    private Executor pool=Executors.newCachedThreadPool();

    private HashSet<EventListener> setListener;
    public EventBus(){
        setListener=new HashSet<EventListener>();
    }
    public void register(EventListener listener){
        setListener.add(listener);
    }
    public void unregister(EventListener listener){
        setListener.remove(listener);
    }
    public void sendEvent(EventBean obj){
        pool.execute(new MyRunnable(obj));
    }
    private class MyRunnable implements  Runnable{
        private EventBean obj;
        public MyRunnable(EventBean obj){
            this.obj=obj;
        }
        @Override
        public void run() {
            for (EventListener listener:setListener){
                if(listener!=null){
                    listener.onEvent(obj);
                }
            }
        }
    }
    /**
     * 清空
     */
    public void clear(){
        setListener.clear();
    }
}
