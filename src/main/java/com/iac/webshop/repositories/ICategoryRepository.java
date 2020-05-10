package com.iac.webshop.repositories;

import com.iac.webshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "category")
public interface ICategoryRepository extends JpaRepository<Category, Long> {


}

