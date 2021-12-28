package com.supermarket.services.productservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Product {
    private String productName;
    private Double price;
    private String category;
    private String size;
    private Long amount;
    private String trademark;
}
