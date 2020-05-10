package com.iac.webshop.repositories;

import com.iac.webshop.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "address")
public interface IAddressRepository extends JpaRepository<Address, Long> {

}

