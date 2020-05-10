package com.iac.webshop.repositories;

import com.iac.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "product")
public interface IProductRepository extends JpaRepository<Product, Long> {
}

