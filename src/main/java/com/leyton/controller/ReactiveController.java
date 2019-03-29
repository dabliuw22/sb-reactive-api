
package com.leyton.controller;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leyton.entity.User;
import com.leyton.service.inter.ReactiveClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(
        value = {
            "/rx/users"
        })
public class ReactiveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveController.class);

    private static final String BASE_URL = "http://localhost:8080/users";

    @Autowired
    private ReactiveClient reactiveClient;

    @GetMapping()
    public Flux<User> getRxAll() {
        return reactiveClient.getFlux(BASE_URL, "", new LinkedMultiValueMap<>(),
                Collections.emptyMap(), new ParameterizedTypeReference<User>() {
                });
    }

    @GetMapping(
            value = {
                "/names"
            })
    public Mono<List<String>> getRxAllName() {
        Flux<User> response = reactiveClient.getFlux(BASE_URL, "", new LinkedMultiValueMap<>(),
                Collections.emptyMap(), new ParameterizedTypeReference<User>() {
                });
        return response.delayElements(Duration.ZERO, Schedulers.parallel())
                .doOnNext(user -> LOGGER.info("Thread: {}, user: {}",
                        Thread.currentThread().getName(), user))
                .doFinally(user -> LOGGER.info("Terminate"))
                .flatMap(user -> Flux.just(user.getName())).collectList();
    }

    @GetMapping(
            value = {
                "/{id}"
            })
    public Mono<User> getRxUser(@PathVariable(
            value = "id") String id) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        return reactiveClient.getMono(BASE_URL, "/{id}", new LinkedMultiValueMap<>(), uriVariables,
                new ParameterizedTypeReference<User>() {
                });
    }
}
