package com.javatechie.service;

import com.javatechie.entity.Inventory;

import java.util.Optional;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
}
