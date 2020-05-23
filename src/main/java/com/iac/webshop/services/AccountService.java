package com.iac.webshop.services;

import com.iac.webshop.exceptions.AccountNotFoundException;
import com.iac.webshop.models.Account;
import com.iac.webshop.repositories.IAccountRepository;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        account.validate();
        List<Account> accounts = getAllAccounts();
        for (Account existingAccount : accounts ) {
            if (account.getEmail().equals(existingAccount.getEmail())) {
                throw new ValidationException("Email is already in use");
            }
        }
        return accountRepository.save(account);
    }

    @Override
    public Account login(String email, String password) {
        return null;
    }

    @Override
    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException(id));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}