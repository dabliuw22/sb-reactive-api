
package com.leyton.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFlux
@Configuration
public class ReactiveConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
