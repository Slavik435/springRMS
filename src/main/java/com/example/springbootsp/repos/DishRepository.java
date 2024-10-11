package com.example.springbootsp.repos;

import com.example.springbootsp.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    void deleteAllByCategoryId(Long categoryId);
}