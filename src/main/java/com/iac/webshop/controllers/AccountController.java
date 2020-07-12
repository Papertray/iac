package com.iac.webshop.controllers;

import com.iac.webshop.dto.AccountDTO;
import com.iac.webshop.helpers.Utils;
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
    public AccountDTO createAccount(@RequestBody Account account) {
        AccountDTO accountDTO = Utils.convertToDto(accountService.createAccount(account), AccountDTO.class);
        activeMQSender.send(accountDTO.toString());
        return accountDTO;
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
