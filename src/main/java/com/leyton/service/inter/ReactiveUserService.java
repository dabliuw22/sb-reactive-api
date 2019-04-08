
package com.leyton.service.inter;

import java.util.List;

import com.leyton.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserService {

    Flux<User> getAll();

    Mono<User> get(String id);

    Mono<List<String>> getNames();
}
