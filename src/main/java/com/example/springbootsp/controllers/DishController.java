package com.example.springbootsp.controllers;

import com.example.springbootsp.domain.Dish;
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

@RestController
@RequestMapping("/api/dishes")
@AllArgsConstructor
public class DishController {

    private final DishService dishService;
    private final CategoryService categoryService;


    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        return dishService.getDishById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Dish createDish(@RequestBody Dish dish) {
        return categoryService.createDish(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dishDetails) {
        return ResponseEntity.ok(dishService.updateDish(id, dishDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
