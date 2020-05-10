package com.iac.webshop.services;

import com.iac.webshop.models.Category;
import com.iac.webshop.repositories.ICategoryRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    FileManager fileManager;

    @Override
    public Category createCategory(Category category) {
        fileManager.saveFileDB(category.getImage());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category newCategory, long id) throws IOException {
        Optional<Category> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) return null;
        Category category = categories.get();

        fileManager.updateFile(newCategory.getImage(), category.getImage());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) return null;

        //List<Product> products = productRepository.findAllByCategory(category);

        //category.setProducts(products);
        return categories.get();
    }
}