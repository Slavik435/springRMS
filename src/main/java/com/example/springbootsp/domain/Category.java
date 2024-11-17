package com.example.springbootsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public Category(CategoryDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.name = categoryDTO.getName();
    }

    public Category() {
    }

    public CategoryDTO toDto() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        return dto;
    }
}
