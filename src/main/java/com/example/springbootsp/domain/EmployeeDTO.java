package com.example.springbootsp.domain;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    @Nullable
    private Long id;
    private String name;
    private String email;

}