package com.whereim.stepnumber.library.eventbus;

/**
 * Created by HLD on 2015/7/16.
 */
public interface EventListener<T> {
    void onEvent(T obj);
}
