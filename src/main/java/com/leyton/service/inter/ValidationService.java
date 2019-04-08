
package com.leyton.service.inter;

import reactor.core.publisher.Mono;

public interface ValidationService {

    Mono<Boolean> validation(String name);
}
