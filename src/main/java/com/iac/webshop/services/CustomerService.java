package com.iac.webshop.services;

import com.iac.webshop.models.Customer;
import com.iac.webshop.repositories.ICustomerRepository;
import com.iac.webshop.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return null;
    }
}