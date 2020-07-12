package com.iac.webshop.controllers;

import com.iac.webshop.models.Product;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PutMapping("products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long id) {
        return productService.updateProduct(product, id);
    }
    @GetMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
