package com.iac.webshop.repositories;

import com.iac.webshop.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}

