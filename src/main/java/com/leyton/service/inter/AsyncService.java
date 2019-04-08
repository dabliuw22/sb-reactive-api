
package com.leyton.service.inter;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AsyncService {

    Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);

    CompletableFuture<Boolean> getResult();

    default void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            LOGGER.error("Error");
        }
    }
}
