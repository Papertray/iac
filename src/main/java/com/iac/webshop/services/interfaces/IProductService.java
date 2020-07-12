package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product addProduct(Product product);

    Product updateProduct(Product product, long id);
    Product updateProductWithCategory(Product product, long id, long categoryId);

    Product getProductById(long id);
}
