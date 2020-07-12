package com.iac.webshop.controllers;

import com.iac.webshop.models.Category;
import com.iac.webshop.models.Product;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    ActiveMQSender activeMQSender;

    @PostMapping("categories")
    public Category createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        activeMQSender.send(newCategory.toString());
        return newCategory;
    }

    @GetMapping("categories/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
}
