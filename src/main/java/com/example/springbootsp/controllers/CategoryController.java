package com.example.springbootsp.controllers;

import com.example.springbootsp.domain.Category;
import com.example.springbootsp.domain.CategoryDTO;
import com.example.springbootsp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(Category::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(category.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(new Category(categoryDTO));
        return category.toDto();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.updateCategory(id, new Category(categoryDTO));
        return ResponseEntity.ok(updatedCategory.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}