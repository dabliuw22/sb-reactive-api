
package com.leyton.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyton.entity.Product;
import com.leyton.repository.ProductRepository;
import com.leyton.service.inter.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Flux<Product> getAll() {
        return productRepository.findAll();
    }
}
