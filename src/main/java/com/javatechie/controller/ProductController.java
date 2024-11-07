package com.javatechie.controller;

import com.javatechie.dto.ProductDetailDTO;
import com.javatechie.facade.ProductAsyncFacade;
import com.javatechie.facade.ProductFacade;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductAsyncFacade productAsyncFacade;

    public ProductController(ProductFacade productFacade, ProductAsyncFacade productAsyncFacade) {
        this.productFacade = productFacade;
        this.productAsyncFacade = productAsyncFacade;
    }

    @GetMapping("/{id}/sync")
    public ResponseEntity<ProductDetailDTO> getProductSync(@PathVariable Long id) {
        log.info("Rest request to get product by id sync: {}", id);
        return ResponseEntity.ok(productFacade.getProductDetail(id));
    }

    @GetMapping("/{id}/async")
    public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id) {
        log.info("Rest request to get product by id async: {}", id);
        return ResponseEntity.ok(productAsyncFacade.getProductDetailById(id));
    }
}
