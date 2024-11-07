package com.javatechie.service;

import com.javatechie.entity.Price;

import java.util.Optional;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}
