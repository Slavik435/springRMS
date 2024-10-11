package com.example.springbootsp.services;

import com.example.springbootsp.domain.Dish;
import com.example.springbootsp.exception.ResourceNotFoundException;
import com.example.springbootsp.repos.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    @Transactional
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish dishDetails) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id :: " + id));

        dish.setName(dishDetails.getName());
        dish.setCategory(dishDetails.getCategory());
        dish.setPrice(dishDetails.getPrice());
        dish.setAvailableStock(dishDetails.getAvailableStock());

        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id :: " + id));
        dishRepository.delete(dish);
    }

    public void deleteAllByCategoryId(Long categoryId) {
        dishRepository.deleteAllByCategoryId(categoryId);
    }
}
