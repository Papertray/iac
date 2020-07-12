package com.iac.webshop.controllers;

import com.iac.webshop.models.Product;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;
    @Autowired
    ActiveMQSender activeMQSender;

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        activeMQSender.send(newProduct.toString());
        return newProduct;
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PutMapping("products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long id) {
        return productService.updateProduct(product, id);
    }

    @PutMapping("products/{id}/category/{categoryId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long id, @PathVariable long categoryId) {
        return productService.updateProductWithCategory(product, id, categoryId);
    }
    @GetMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
