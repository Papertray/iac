package com.iac.webshop.controllers;

import com.iac.webshop.models.Category;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @PostMapping("category")
    public void createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
    }

    @GetMapping("category/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }
}
