package com.example.springbootsp.services;

import com.example.springbootsp.domain.Category;
import com.example.springbootsp.domain.Dish;
import com.example.springbootsp.exception.ResourceNotFoundException;
import com.example.springbootsp.repos.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final DishService dishService;

    private final CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));

        category.setName(categoryDetails.getName());

        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
        dishService.deleteAllByCategoryId(category.getId());

        categoryRepository.delete(category);
    }


    @Transactional
    public Dish createDish(Dish dish) {
        dish.setCategory(categoryRepository.findById(dish.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + dish.getCategory().getId())));
        return dishService.createDish(dish);
    }

}
