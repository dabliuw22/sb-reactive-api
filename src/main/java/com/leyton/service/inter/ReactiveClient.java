
package com.leyton.service.inter;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveClient {

    <T1> Flux<T1> getFlux(String baseUrl, String uri, MultiValueMap<String, String> params,
            Map<String, ?> uriVariables, ParameterizedTypeReference<T1> responseType);

    <T1> Mono<T1> getMono(String baseUrl, String uri, MultiValueMap<String, String> params,
            Map<String, ?> uriVariables, ParameterizedTypeReference<T1> responseType);

    <T1, T2> Flux<T1> postFlux(String baseUrl, String uri, T2 body,
            ParameterizedTypeReference<T1> responseType);

    <T1, T2> Mono<T1> postMono(String baseUrl, String uri, T2 body,
            ParameterizedTypeReference<T1> responseType);
}
