
package com.leyton.service.imple;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.leyton.service.inter.AsyncService;

@Service
public class AsyncServiceImp implements AsyncService {

    @Async
    @Override
    public CompletableFuture<Boolean> getResult() {
        LOGGER.info("get result...");
        sleep(1000);
        return CompletableFuture.completedFuture(true);
    }
}
