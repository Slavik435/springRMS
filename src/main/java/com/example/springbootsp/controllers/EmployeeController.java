package com.example.springbootsp.controllers;

import com.example.springbootsp.domain.Employee;
import com.example.springbootsp.domain.EmployeeDTO;
import com.example.springbootsp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.ok(employee.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(new Employee(employeeDTO));
        return employee.toDto();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, new Employee(employeeDTO));
        return ResponseEntity.ok(updatedEmployee.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}