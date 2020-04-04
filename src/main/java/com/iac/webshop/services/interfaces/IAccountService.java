package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Account;

public interface IAccountService {
    Account createAccount(Account account);

    Account login(String email, String password);
}
