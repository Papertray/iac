package com.iac.webshop.controllers;

import com.iac.webshop.models.Customer;
import com.iac.webshop.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @RequestMapping("/{id}")
    public List<Customer> getCustomers(@PathVariable String id) {
        return customerService.getCustomers();
    }

}
