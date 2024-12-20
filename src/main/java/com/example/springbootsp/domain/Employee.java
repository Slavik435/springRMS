package com.example.springbootsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    public Employee(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.name = employeeDTO.getName();
        this.email = employeeDTO.getEmail();
    }

    public Employee() {
    }

    public EmployeeDTO toDto() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setEmail(this.email);
        return dto;
    }
}