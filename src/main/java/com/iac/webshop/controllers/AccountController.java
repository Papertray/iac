package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/account")
    public void createAccount(@RequestBody Account account) { accountService.createAccount(account);
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getAccountById(id);
    }

}
