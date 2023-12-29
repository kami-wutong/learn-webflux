package com.ryanqy.backpressure;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * @author tong.wu
 * created on 2023/12/29
 */

public class Test {
    private static int SUBSCRIBER_CAPACITY = 5;
    private static int PUBLISH_TIME = 200;
    private static int CONSUME_TIME = 1000;

    private EventSource source = new EventSource();

    public Flux<EventSource.Event> createFlux(FluxSink.OverflowStrategy backpressure) {
        return Flux.create(sink -> {
            source.registry(new EventListener() {
                @Override
                public void onNext(EventSource.Event event) {
                    sink.next(event);
                }

                @Override
                public void onComplete() {
                    sink.complete();
                }
            });
        }, backpressure);
    }

    public void testBuffer() {
        createFlux(FluxSink.OverflowStrategy.BUFFER).subscribe(new SlowSubscriber(SUBSCRIBER_CAPACITY, CONSUME_TIME));
        new FastPublisher(source, PUBLISH_TIME).send();

    }

    public void testDrop() {
        createFlux(FluxSink.OverflowStrategy.DROP).subscribe(new SlowSubscriber(SUBSCRIBER_CAPACITY, CONSUME_TIME));
        new FastPublisher(source, PUBLISH_TIME).send();
    }

    public void testError() {
        createFlux(FluxSink.OverflowStrategy.ERROR).subscribe(new SlowSubscriber(SUBSCRIBER_CAPACITY, CONSUME_TIME));
        new FastPublisher(source, PUBLISH_TIME).send();
    }

    public void testLatest() {
        createFlux(FluxSink.OverflowStrategy.LATEST).subscribe(new SlowSubscriber(SUBSCRIBER_CAPACITY, CONSUME_TIME));
        new FastPublisher(source, PUBLISH_TIME).send();
    }

    public void testIgnore() {
        createFlux(FluxSink.OverflowStrategy.IGNORE).subscribe(new SlowSubscriber(SUBSCRIBER_CAPACITY, CONSUME_TIME));
        new FastPublisher(source, PUBLISH_TIME).send();
    }

    public static void main(String[] args) {
//        new Test().testBuffer();
//        new Test().testDrop();
//        new Test().testError();
//        new Test().testLatest();
        new Test().testIgnore();
    }

}
