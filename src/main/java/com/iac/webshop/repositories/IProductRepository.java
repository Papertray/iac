package com.iac.webshop.repositories;

import com.iac.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}

