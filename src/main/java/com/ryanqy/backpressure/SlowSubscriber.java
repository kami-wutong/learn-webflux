package com.ryanqy.backpressure;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.wu
 * created on 2023/12/29
 */

public class SlowSubscriber extends BaseSubscriber<EventSource.Event> {

    private int capacity;

    private int processTime;

    private ThreadPoolExecutor pool;

    public SlowSubscriber(int capacity, int processTime) {
        this.capacity = capacity;
        this.processTime = processTime;
        this.pool = new ThreadPoolExecutor(1, 1, 1,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(capacity));
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("========== request " + capacity + " ==========");
        request(capacity);
    }

    @Override
    protected void hookOnNext(EventSource.Event event) {
        pool.submit(() -> {
            System.out.println(">>> receive event: " + event.getMsg());
            try {
                TimeUnit.MILLISECONDS.sleep(processTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("========== request 1 ==========");
            request(1);
        });
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Complete");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    protected void hookOnCancel() {
        System.out.println("Cancel");
    }
}
