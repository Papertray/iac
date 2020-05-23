package com.iac.webshop.repositories;

import com.iac.webshop.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {

}

