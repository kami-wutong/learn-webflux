package com.ryanqy.backpressure;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tong.wu
 * created on 2023/12/29
 */
public class EventSource {

    private List<EventListener> listeners = new ArrayList<>();

    public void registry(EventListener listener) {
        listeners.add(listener);
    }

    public void next(Event event) {
        System.out.println("<<< send event: " + event.getMsg());
        listeners.forEach(l -> l.onNext(event));
    }

    public void complete() {
        listeners.forEach(EventListener::onComplete);
    }

    @Data
    @AllArgsConstructor
    public static class Event {
        private String msg;
    }
}

interface  EventListener {
    void onNext(EventSource.Event event);

    void onComplete();

}
