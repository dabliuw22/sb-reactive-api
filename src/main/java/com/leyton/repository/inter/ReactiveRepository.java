
package com.leyton.repository.inter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveRepository<T, ID> {

    Mono<T> findById(ID id);

    Flux<T> findAll();
}
