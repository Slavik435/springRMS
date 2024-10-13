package com.example.springbootsp.services.sceduled;

import com.example.springbootsp.repos.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class DishCronService {

    private final DishRepository dishRepository;

    @Value("${price.increase}")
    private BigDecimal priceIncrease;

    @Scheduled(cron = "0 0 0 1 * ?") //runs every first day of the month
    public void increaseDishPrice() {
        dishRepository.findAll().forEach(dish -> {
            dish.setPrice(dish.getPrice().multiply(priceIncrease));
            dishRepository.save(dish);
        });
    }
}