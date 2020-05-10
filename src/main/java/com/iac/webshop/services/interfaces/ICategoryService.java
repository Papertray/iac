package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Category;

import java.io.IOException;

public interface ICategoryService {
    Category createCategory(Category category);

    Category getCategoryById(long id);

    Category updateCategory(Category newCategory, long id) throws IOException;
}
