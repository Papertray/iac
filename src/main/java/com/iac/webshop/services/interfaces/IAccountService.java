package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Account;

import java.util.List;

public interface IAccountService {
    Account createAccount(Account account);

    Account login(String email, String password);

    Account getAccountById(long id);

    List<Account> getAllAccounts();

}
