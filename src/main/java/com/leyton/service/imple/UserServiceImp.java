
package com.leyton.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyton.entity.User;
import com.leyton.repository.UserRepository;
import com.leyton.service.inter.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> create(Mono<User> user) {
        return user.flatMap(userRepository::save);
    }

    @Override
    public Mono<User> get(Mono<String> id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> update(Mono<User> user) {
        return user.flatMap(userRepository::save);
    }

    @Override
    public Mono<Void> delete(Mono<String> id) {
        return userRepository.deleteById(id);
    }
}
