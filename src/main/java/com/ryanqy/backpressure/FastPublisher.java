package com.ryanqy.backpressure;

import java.util.concurrent.TimeUnit;

/**
 * @author tong.wu
 * created on 2023/12/29
 */

public class FastPublisher {

    private EventSource source;

    private int processTime;

    public FastPublisher(EventSource source, int processTime) {
        this.source = source;
        this.processTime = processTime;
    }

    public void send() {
        for (int i = 0; i < 10; i++) {
            source.next(new EventSource.Event("event " + i));
            try {
                TimeUnit.MILLISECONDS.sleep(processTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        source.complete();
    }
}
