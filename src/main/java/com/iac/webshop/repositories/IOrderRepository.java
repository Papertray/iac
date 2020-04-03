package com.iac.webshop.repositories;

import com.iac.webshop.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderLine, Long> {


}

