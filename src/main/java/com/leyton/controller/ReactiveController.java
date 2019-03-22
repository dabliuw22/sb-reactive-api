
package com.leyton.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

@RestController
@RequestMapping(
        value = {
            "/rx/users"
        })
public class ReactiveController {

    private static final String BASE_URL = "http://localhost:8080/users";

    @Autowired
    private ReactiveClient reactiveClient;

    @GetMapping
    public Flux<User> getRxAll() {
        return reactiveClient.getFlux(BASE_URL, "", new LinkedMultiValueMap<>(),
                Collections.emptyMap(), new ParameterizedTypeReference<User>() {
                });
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
