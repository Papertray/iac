package com.iac.webshop.services;

import com.iac.webshop.exceptions.ProductNotFoundException;
import com.iac.webshop.models.Category;
import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.ICategoryRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.math.BigDecimal;
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
        validatePrice(product.getPrice(), product.getMinimumPrice());

        Optional<Category> category = categoryRepository.findById(defaultCategoryId);
        category.ifPresent(product::setCategory);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, long id) {
        ValidateProduct(product);

        return productRepository.findById(id)
                .map(existingProduct -> { existingProduct.copyFrom(product);
                    return productRepository.save(existingProduct);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void ValidateProduct(Product product) {
        validatePrice(product.getPrice(), product.getMinimumPrice());
    }

    private void validatePrice(BigDecimal price, BigDecimal minimumPrice) {
        if (price.scale() != 2) {
            throw new ValidationException("Two numbers after decimal expected");
        }

        if (price.compareTo(minimumPrice) < 0) {
            throw new ValidationException("Price lower than minimum price");
        }
    }
}