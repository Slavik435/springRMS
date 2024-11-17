package com.example.springbootsp.domain;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DishDTO {
    @Nullable
    private Long id;
    private String name;
    private Category category;
    private BigDecimal price;
    private Integer availableStock;
}