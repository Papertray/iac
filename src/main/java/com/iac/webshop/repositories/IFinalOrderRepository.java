package com.iac.webshop.repositories;

import com.iac.webshop.models.FinalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinalOrderRepository extends JpaRepository<FinalOrder, Long> {

}

