
package com.leyton.domain;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.BaseSubscriber;

public class BackpressureSubscriber<T> extends BaseSubscriber<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackpressureSubscriber.class);

    private int size;

    private int count;

    public BackpressureSubscriber(int size) {
        this.size = size;
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        LOGGER.info("[Backpressure] Trying to subscribe... ");
        request(size);
    }

    @Override
    protected void hookOnNext(T value) {
        count++;
        LOGGER.info("[Backpressure] Element {}... ", count);
        if (count % size == 0)
            request(size);
        if (count >= 100)
            cancel();
    }

    @Override
    protected void hookOnComplete() {
        LOGGER.info("[Backpressure] Complete... ");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        LOGGER.error("[Backpressure] Error... ", throwable);
    }
}
