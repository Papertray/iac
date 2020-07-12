package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Category;
import com.iac.webshop.models.Product;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);

    Category getCategoryById(long id);

    List<Category> getCategories();

    Category updateCategory(Category newCategory, long id) throws IOException;
}
