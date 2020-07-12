package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.IAccountService;
import org.apache.activemq.ActiveMQDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;
    @Autowired
    ActiveMQSender activeMQSender;

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        Account result = accountService.createAccount(account);
        activeMQSender.send(result.getEmail());
        return result;
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
