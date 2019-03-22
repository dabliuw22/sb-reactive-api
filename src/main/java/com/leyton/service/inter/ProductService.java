
package com.leyton.service.inter;

import com.leyton.entity.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> get(Integer id);

    Flux<Product> getAll();
}
