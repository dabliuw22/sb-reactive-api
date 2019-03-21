
package com.leyton.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyton.model.User;
import com.leyton.repository.imple.UserReactiveRepository;
import com.leyton.service.inter.ReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveServiceImp implements ReactiveService {

    @Autowired
    private UserReactiveRepository reactiveRepository;

    @Override
    public Mono<User> findById(Long id) {
        return reactiveRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return reactiveRepository.findAll();
    }
}
