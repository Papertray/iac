package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Category;

public interface ICategoryService {
    Category createCategory(Category category);

    Category getCategoryById(long id);
}
