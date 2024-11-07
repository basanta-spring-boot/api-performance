package com.javatechie.facade;

import com.javatechie.dto.ProductDetailDTO;
import com.javatechie.entity.Inventory;
import com.javatechie.entity.Price;
import com.javatechie.entity.Product;
import com.javatechie.service.InventoryService;
import com.javatechie.service.PriceService;
import com.javatechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ProductAsyncFacade {


    private final ProductService productService;

    private final InventoryService inventoryService;

    private final PriceService priceService;

    public ProductAsyncFacade(ProductService productService, InventoryService inventoryService, PriceService priceService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.priceService = priceService;
    }

    // Future for fetching the product details by productId
    private CompletableFuture<Product> getProductById(Long productId) {
        return CompletableFuture.supplyAsync(() -> productService.findById(productId));
    }

    // Future for fetching the price details by productId
    public CompletableFuture<Price> getPriceByProductId(Long productId) {
        return CompletableFuture.supplyAsync(() -> priceService.getPriceByProductId(productId));
    }

    // Future for fetching the inventory details by productId
    public CompletableFuture<Inventory> getInventoryByProductId(Long productId) {
        return CompletableFuture.supplyAsync(() -> inventoryService.getInventoryByProductId(productId));
    }


    public ProductDetailDTO getProductDetailById(Long productId) {
        // Fetch all asynchronously
        CompletableFuture<Product> productFuture = getProductById(productId);
        CompletableFuture<Price> priceFuture = getPriceByProductId(productId);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

        // Wait for all futures to complete
        CompletableFuture.allOf(productFuture, priceFuture, inventoryFuture).join();

        // Combine the results
        Product product = productFuture.join();
        Price price = priceFuture.join();
        Inventory inventory = inventoryFuture.join();

        int availableQuantity = inventory.getAvailableQuantity();
        double priceValue = price.getPrice();

        // Build and return ProductDetail
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(), availableQuantity,
                priceValue, product.getStatus());
    }
}
