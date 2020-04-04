package com.iac.webshop.services;

import com.iac.webshop.models.Account;
import com.iac.webshop.models.Category;
import com.iac.webshop.repositories.IAccountRepository;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account login(String email, String password) {
        return null;
    }

    @Override
    public Account getAccountById(long id) {
        Optional<Account> accounts = accountRepository.findById(id);
        return accounts.isEmpty() ? null : accounts.get();
    }


}