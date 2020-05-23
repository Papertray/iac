package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    //test for pushing

    @Autowired
    IAccountService accountService;

    @PostMapping("/accounts")
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getAccountById(id);
    }

}
