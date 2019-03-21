
package com.leyton.controller;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leyton.model.User;
import com.leyton.service.inter.ReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RestController
public class ReactiveController {

    @Autowired
    private ReactiveService reactiveService;

    @GetMapping(
            value = {
                "/fibonacci"
            })
    public Publisher<Long> getFibonacci() {
        Flux<Long> source = Flux.generate(() -> Tuples.<Long, Long> of(0L, 1L), (state, sink) -> {
            if (state.getT1() < 0) {
                sink.complete();
            } else {
                sink.next(state.getT1());
            }
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
        return source;
    }

    @GetMapping(
            value = "/users/{id}")
    public Mono<User> getById(@PathVariable(
            value = "id") Long id) {
        return reactiveService.findById(id);
    }

    @GetMapping(
            value = "/users")
    public Flux<User> getAll() {
        return reactiveService.findAll();
    }
}
