package com.iac.webshop.controllers;

import com.iac.webshop.models.Product;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping("Product")
    public HttpStatus addProduct(Product product) {
        return productService.addProduct(product) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("Product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }
}
