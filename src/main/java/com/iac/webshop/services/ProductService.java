package com.iac.webshop.services;

import com.iac.webshop.exceptions.NotFoundException;
import com.iac.webshop.exceptions.ProductNotFoundException;
import com.iac.webshop.models.Category;
import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.ICategoryRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    @Value("${default.category.id}")
    private long defaultCategoryId;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> category = categoryRepository.findById(defaultCategoryId);
        category.ifPresent(product::setCategory);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Optional<Product> product = productRepository.findById(id);
        return product.isEmpty() ? null : product.get();
    }
}