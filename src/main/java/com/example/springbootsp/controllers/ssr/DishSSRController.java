package com.example.springbootsp.controllers.ssr;

import com.example.springbootsp.domain.Dish;
import com.example.springbootsp.domain.DishDTO;
import com.example.springbootsp.exception.ResourceNotFoundException;
import com.example.springbootsp.services.CategoryService;
import com.example.springbootsp.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dishes")
@AllArgsConstructor
public class DishSSRController {

    private final DishService dishService;
    private final CategoryService categoryService;


    @GetMapping
    public String listDishes(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "listDishes";
    }

    @GetMapping("/new")
    public String showDishForm(Model model) {
        model.addAttribute("dish", new Dish(new DishDTO()));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dishForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DishDTO dish = dishService.getDishById(id)
                .map(Dish::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
        model.addAttribute("dish", dish);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dishForm";
    }

    @PostMapping("/add")
    public String addDish(@ModelAttribute DishDTO dishDTO) {
        dishService.createDish(dishDTO);
        return "redirect:/dishes";
    }

    @PostMapping("/update/{id}")
    public String updateDish(@PathVariable Long id, @ModelAttribute DishDTO dishDTO) {
        dishService.updateDish(id, dishDTO);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dishes";
    }
}