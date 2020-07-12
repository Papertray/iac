package com.iac.webshop.controllers;

import com.iac.webshop.models.Category;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    ActiveMQSender activeMQSender;

    @PostMapping("category")
    public Category createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        activeMQSender.send(newCategory.toString());
        return newCategory;
    }

    @GetMapping("category/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }
}
