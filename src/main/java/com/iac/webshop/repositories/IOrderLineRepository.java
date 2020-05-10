package com.iac.webshop.repositories;

import com.iac.webshop.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "order_line")
public interface IOrderLineRepository extends JpaRepository<OrderLine, Long> {


}

