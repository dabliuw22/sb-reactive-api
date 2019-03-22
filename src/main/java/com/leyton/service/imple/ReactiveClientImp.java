
package com.leyton.service.imple;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.leyton.service.inter.ReactiveClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveClientImp implements ReactiveClient {

    @Override
    public <T1> Flux<T1> getFlux(String baseUrl, String uri, MultiValueMap<String, String> params,
            Map<String, ?> uriVariables, ParameterizedTypeReference<T1> responseType) {
        return get(baseUrl, uri, params, uriVariables).bodyToFlux(responseType);
    }

    @Override
    public <T1> Mono<T1> getMono(String baseUrl, String uri, MultiValueMap<String, String> params,
            Map<String, ?> uriVariables, ParameterizedTypeReference<T1> responseType) {
        return get(baseUrl, uri, params, uriVariables).bodyToMono(responseType);
    }

    @Override
    public <T1, T2> Flux<T1> postFlux(String baseUrl, String uri, T2 body,
            ParameterizedTypeReference<T1> responseType) {
        return post(baseUrl, uri, body).bodyToFlux(responseType);
    }

    @Override
    public <T1, T2> Mono<T1> postMono(String baseUrl, String uri, T2 body,
            ParameterizedTypeReference<T1> responseType) {
        return post(baseUrl, uri, body).bodyToMono(responseType);
    }

    private ResponseSpec get(String baseUrl, String uri, MultiValueMap<String, String> params,
            Map<String, ?> uriVariables) {
        return WebClient.create(baseUrl).get()
                .uri(builder -> builder.path(uri).queryParams(params).build(uriVariables))
                .accept(MediaType.APPLICATION_JSON).retrieve();
    }

    private <T2> ResponseSpec post(String baseUrl, String uri, T2 body) {
        return WebClient.create(baseUrl).post().uri(uri).body(BodyInserters.fromObject(body))
                .accept(MediaType.APPLICATION_JSON).retrieve();
    }
}
