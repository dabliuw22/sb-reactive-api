
package com.leyton.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.leyton.entity.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
