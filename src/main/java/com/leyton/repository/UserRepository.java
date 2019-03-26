
package com.leyton.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.leyton.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {

    @Override
    @Query(
            value = "SELECT * FROM users WHERE id = $1")
    Mono<User> findById(String id);

    @Override
    @Query(
            value = "SELECT * FROM users")
    Flux<User> findAll();
}
