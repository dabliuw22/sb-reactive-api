
package com.leyton.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.leyton.route.ReactiveRouter;

@EnableWebFlux
@Configuration
public class ReactiveConfiguration {

    @Bean
    public RouterFunction<ServerResponse> userRoute(ReactiveRouter reactiveRouter) {
        return reactiveRouter.userRoute();
    }
}
