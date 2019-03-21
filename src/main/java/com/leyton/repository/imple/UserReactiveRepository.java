
package com.leyton.repository.imple;

import org.springframework.stereotype.Repository;

import com.leyton.model.User;
import com.leyton.repository.inter.ReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserReactiveRepository implements ReactiveRepository<User, Long> {

    @Override
    public Mono<User> findById(Long id) {
        return Mono.just(new User(id, "User" + id));
    }

    @Override
    public Flux<User> findAll() {
        return Flux.just(new User(1L, "User1"), new User(2L, "User2"));
    }
}
