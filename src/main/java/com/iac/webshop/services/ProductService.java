package com.iac.webshop.services;

import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public boolean addProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }
}