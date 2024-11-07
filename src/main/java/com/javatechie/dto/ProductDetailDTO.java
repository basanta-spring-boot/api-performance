package com.javatechie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

    private Long id;

    private String categoryName;

    private String name;

    private String description;

    private Integer availableQuantity;

    private Double price;

    private String status;
}
