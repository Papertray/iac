package com.iac.webshop.repositories;

import com.iac.webshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {


}

