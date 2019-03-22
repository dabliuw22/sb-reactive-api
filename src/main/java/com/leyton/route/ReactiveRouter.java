
package com.leyton.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.leyton.handler.UserHandler;

@Component
public class ReactiveRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveRouter.class);

    @Autowired
    private UserHandler userHandler;

    public RouterFunction<ServerResponse> userRoute() {
        return RouterFunctions.nest(RequestPredicates.path("/users"), RouterFunctions.route()
                .GET("/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON),
                        userHandler::get)
                .GET("", RequestPredicates.accept(MediaType.APPLICATION_JSON), userHandler::all)
                .POST("", RequestPredicates.accept(MediaType.APPLICATION_JSON), userHandler::create)
                .PUT("", RequestPredicates.accept(MediaType.APPLICATION_JSON), userHandler::update)
                .DELETE("/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON),
                        userHandler::delete)
                .build()).filter((request, next) -> {
                    LOGGER.info("filter: {}", request);
                    return next.handle(request);
                });
    }
}
