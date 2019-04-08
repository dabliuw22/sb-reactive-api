
package com.leyton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leyton.entity.User;
import com.leyton.service.inter.ReactiveUserService;
import com.leyton.service.inter.ValidationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(
        value = {
            "/rx/users"
        })
public class ReactiveController {

    @Autowired
    private ReactiveUserService reactiveUserService;

    @Autowired
    private ValidationService validationService;

    @GetMapping()
    public Flux<User> getRxAll() {
        return reactiveUserService.getAll();
    }

    @GetMapping(
            value = {
                "/{id}"
            })
    public Mono<User> getRxUser(@PathVariable(
            value = "id") String id) {
        return reactiveUserService.get(id);
    }

    @GetMapping(
            value = {
                "/names"
            })
    public Mono<List<String>> getRxAllName() {
        return reactiveUserService.getNames();
    }

    @GetMapping(
            value = {
                "/validation"
            })
    public Mono<Boolean> getValidation(@RequestParam(
            value = "name") String name) {
        return validationService.validation(name);
    }
}
