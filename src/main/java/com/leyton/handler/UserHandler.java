
package com.leyton.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.leyton.entity.User;
import com.leyton.service.inter.UserService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return userService.create(request.bodyToMono(User.class))
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(user))
                        .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return userService.get(Mono.just(request.pathVariable("id")))
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(user))
                        .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(userService.getAll(), User.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return userService.update(request.bodyToMono(User.class))
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(user))
                        .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return userService.delete(Mono.just(request.pathVariable("id"))).flatMap(v -> ServerResponse
                .noContent().build().switchIfEmpty(ServerResponse.notFound().build()));
    }
}
