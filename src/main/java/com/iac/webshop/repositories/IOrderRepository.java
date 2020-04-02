package com.iac.webshop.repositories;

import com.iac.webshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {


}

