package com.example.springbootsp.controllers;

import com.example.springbootsp.domain.Dish;
import com.example.springbootsp.domain.DishDTO;
import com.example.springbootsp.services.CategoryService;
import com.example.springbootsp.services.DishService;
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
@RequestMapping("/api/dishes")
@AllArgsConstructor
public class DishController {

    private final DishService dishService;
    private final CategoryService categoryService;

    @GetMapping
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes().stream()
                .map(Dish::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Long id) {
        return dishService.getDishById(id)
                .map(dish -> ResponseEntity.ok(dish.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DishDTO createDish(@RequestBody DishDTO dishDTO) {
        Dish dish = dishService.createDish(dishDTO);
        return dish.toDto();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable Long id, @RequestBody DishDTO dishDTO) {
        Dish updatedDish = dishService.updateDish(id, dishDTO);
        return ResponseEntity.ok(updatedDish.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}