
package com.leyton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leyton.entity.Product;
import com.leyton.service.inter.ProductService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(
        value = {
            "/products"
        })
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product> getAll() {
        return productService.getAll();
    }
}
