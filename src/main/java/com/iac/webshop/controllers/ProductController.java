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

    @PostMapping("product")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
