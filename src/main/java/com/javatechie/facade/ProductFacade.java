package com.javatechie.facade;

import com.javatechie.dto.ProductDetailDTO;
import com.javatechie.entity.Inventory;
import com.javatechie.entity.Price;
import com.javatechie.entity.Product;
import com.javatechie.service.InventoryService;
import com.javatechie.service.PriceService;
import com.javatechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ProductFacade {


    private final ProductService productService;

    private final InventoryService inventoryService;

    private final PriceService priceService;

    public ProductFacade(ProductService productService, InventoryService inventoryService, PriceService priceService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.priceService = priceService;
    }


    public ProductDetailDTO getProductDetail(Long productId) {

        log.info("Facade for getting product detail for the productId {}", productId);

        // Fetch Product detail
        Product product = productService.findById(productId);

        // Fetch Price detail for the product
        Price price = priceService.getPriceByProductId(productId);

        // Fetch Inventory detail for the product
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        // Combine the details into ProductDetail if product exists

            return new ProductDetailDTO(productId, product.getCategory().getName(),
                    product.getName(), product.getDescription(), inventory.getAvailableQuantity(),
                    price.getPrice(), product.getStatus());
    }
}
