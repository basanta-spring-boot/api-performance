package com.javatechie.service;

import com.javatechie.entity.Product;

import java.util.Optional;

public interface ProductService {
    Product findById(Long id);
}
