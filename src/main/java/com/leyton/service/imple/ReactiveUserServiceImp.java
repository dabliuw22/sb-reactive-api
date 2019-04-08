
package com.leyton.service.imple;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import com.leyton.domain.BackpressureSubscriber;
import com.leyton.entity.User;
import com.leyton.service.inter.ReactiveClient;
import com.leyton.service.inter.ReactiveUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveUserServiceImp implements ReactiveUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveUserServiceImp.class);

    private static final String BASE_URL = "http://localhost:8080/users";

    private static final int BACKPRESSURE_SIZE = 2;

    @Autowired
    private ReactiveClient reactiveClient;

    @Override
    public Flux<User> getAll() {
        return reactiveClient.getFlux(BASE_URL, "", new LinkedMultiValueMap<>(),
                Collections.emptyMap(), new ParameterizedTypeReference<User>() {
                });
    }

    @Override
    public Mono<User> get(String id) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        return reactiveClient.getMono(BASE_URL, "/{id}", new LinkedMultiValueMap<>(), uriVariables,
                new ParameterizedTypeReference<User>() {
                });
    }

    @Override
    public Mono<List<String>> getNames() {
        Flux<User> response = reactiveClient.getFlux(BASE_URL, "", new LinkedMultiValueMap<>(),
                Collections.emptyMap(), new ParameterizedTypeReference<User>() {
                });
        response.subscribe(new BackpressureSubscriber<>(BACKPRESSURE_SIZE));
        /*
         * return response.delayElements(Duration.ZERO, Schedulers.parallel()) .doOnNext(user ->
         * LOGGER.info("[Parallel] Thread: {}, user: {}", Thread.currentThread().getName(), user))
         * .doFinally(user -> LOGGER.info("[Parallel] Terminate")) .flatMap(user ->
         * Flux.just(user.getName())).collectList()
         */
        return response.parallel().runOn(Schedulers.parallel())
                .doOnNext(user -> LOGGER.info("[Parallel] Thread: {}, user: {}",
                        Thread.currentThread().getName(), user))
                .flatMap(user -> Flux.just(user.getName())).sequential()
                .doFinally(name -> LOGGER.info("[Sequential] Terminate")).collectList();
    }
}
