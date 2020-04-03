package com.iac.webshop.repositories;

import com.iac.webshop.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderLineRepository extends JpaRepository<OrderLine, Long> {


}

