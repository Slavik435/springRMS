package com.example.springbootsp.domain;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    @Nullable
    private Long id;
    private String name;

}