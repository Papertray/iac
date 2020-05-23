package com.iac.webshop.repositories;

import com.iac.webshop.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiscountRepository extends JpaRepository<Discount, Long> {


}

