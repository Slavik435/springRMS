package com.example.springbootsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    private BigDecimal price;

    private Integer availableStock;

    public Dish(DishDTO dishDTO) {
        this.id = dishDTO.getId();
        this.name = dishDTO.getName();
        this.price = dishDTO.getPrice();
        this.availableStock = dishDTO.getAvailableStock();
    }

    public Dish() {
    }

    public DishDTO toDto() {
        DishDTO dto = new DishDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCategory(this.category);
        dto.setPrice(this.price);
        dto.setAvailableStock(this.availableStock);
        return dto;
    }

}