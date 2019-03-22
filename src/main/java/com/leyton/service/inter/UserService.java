
package com.leyton.service.inter;

import com.leyton.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> create(Mono<User> user);

    Mono<User> get(Mono<String> id);

    Flux<User> getAll();

    Mono<User> update(Mono<User> user);

    Mono<Void> delete(Mono<String> id);
}
