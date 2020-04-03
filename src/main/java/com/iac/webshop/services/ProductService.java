package com.iac.webshop.services;

import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> products = productRepository.findById(Long.parseLong(id));
        return products.isEmpty() ? null : products.get();
    }
}