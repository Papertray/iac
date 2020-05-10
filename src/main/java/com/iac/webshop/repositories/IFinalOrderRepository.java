package com.iac.webshop.repositories;

import com.iac.webshop.models.FinalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "final_order")
public interface IFinalOrderRepository extends JpaRepository<FinalOrder, Long> {

}

