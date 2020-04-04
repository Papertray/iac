package com.iac.webshop.services;

import com.iac.webshop.models.Category;
import com.iac.webshop.repositories.ICategoryRepository;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> categories = categoryRepository.findById(id);
        return categories.isEmpty() ? null : categories.get();
    }
}