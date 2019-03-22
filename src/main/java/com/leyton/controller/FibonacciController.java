
package com.leyton.controller;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@RestController
public class FibonacciController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);

    @GetMapping(
            value = {
                "/fibonacci"
            })
    public Publisher<Long> getFibonacci() {
        LOGGER.info("Trying to consume Fibonacci service");
        return Flux.generate(() -> Tuples.<Long, Long> of(0L, 1L), (state, sink) -> {
            if (state.getT1() < 0) {
                sink.complete();
            } else {
                sink.next(state.getT1());
            }
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
    }
}
