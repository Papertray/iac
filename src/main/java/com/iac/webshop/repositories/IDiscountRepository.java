package com.iac.webshop.repositories;

import com.iac.webshop.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "discount")
public interface IDiscountRepository extends JpaRepository<Discount, Long> {


}

