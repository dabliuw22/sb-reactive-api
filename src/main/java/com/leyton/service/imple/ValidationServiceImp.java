
package com.leyton.service.imple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyton.service.inter.AsyncService;
import com.leyton.service.inter.ValidationService;

import reactor.core.publisher.Mono;

@Service
public class ValidationServiceImp implements ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationServiceImp.class);

    @Autowired
    private AsyncService asyncService;

    @Override
    public Mono<Boolean> validation(String name) {
        LOGGER.info("Validation: {}", name);
        return Mono.fromFuture(asyncService.getResult());
    }
}
