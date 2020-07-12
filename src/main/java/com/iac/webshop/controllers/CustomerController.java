package com.iac.webshop.controllers;

import com.iac.webshop.models.Account;
import com.iac.webshop.models.Address;
import com.iac.webshop.models.Customer;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    ICustomerService customerService;
    @Autowired
    ActiveMQSender activeMQSender;

    @RequestMapping("/{id}")
    public List<Customer> getCustomers(@PathVariable String id) {
        return customerService.getCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCostumerByID(@PathVariable long id) { return customerService.getCustomerById(id);}

    @PostMapping("/customer")
    public Customer setCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        activeMQSender.send(newCustomer.toString());
        return newCustomer;
    }
}
