
package com.leyton.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.leyton.entity.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
