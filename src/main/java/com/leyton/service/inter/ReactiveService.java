
package com.leyton.service.inter;

import com.leyton.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveService {

    Mono<User> findById(Long id);

    Flux<User> findAll();
}
