package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

}
