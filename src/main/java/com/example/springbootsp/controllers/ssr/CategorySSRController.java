package com.example.springbootsp.controllers.ssr;

import com.example.springbootsp.domain.Category;
import com.example.springbootsp.domain.CategoryDTO;
import com.example.springbootsp.exception.ResourceNotFoundException;
import com.example.springbootsp.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
class CategorySSRController {

    private final CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories().stream()
                .map(Category::toDto)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        return "listCategories";
    }

    @GetMapping("/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category(new CategoryDTO()));
        return "categoryForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryDTO category = categoryService.getCategoryById(id)
                .map(Category::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.createCategory(new Category(categoryDTO));
        return "redirect:/categories";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, new Category(categoryDTO));
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}