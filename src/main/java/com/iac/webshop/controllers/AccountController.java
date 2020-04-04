package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account) {

        return accountService.createAccount(account);
    }

}
