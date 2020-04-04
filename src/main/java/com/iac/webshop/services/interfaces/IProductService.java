package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    void addProduct(Product product);

    Product getProductById(long id);
}
