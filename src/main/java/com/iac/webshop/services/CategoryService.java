package com.iac.webshop.services;

import com.iac.webshop.models.Category;
import com.iac.webshop.repositories.ICategoryRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    IProductRepository productRepository;


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) return null;

        Category category = categories.get();
        //List<Product> products = productRepository.findAllByCategory(category);

        //category.setProducts(products);
        return category;
    }
}