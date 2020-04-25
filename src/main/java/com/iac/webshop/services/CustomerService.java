package com.iac.webshop.services;

import com.iac.webshop.models.Account;
import com.iac.webshop.models.Customer;
import com.iac.webshop.repositories.IAddressRepository;
import com.iac.webshop.repositories.ICustomerRepository;
import com.iac.webshop.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    IAddressRepository addressRepository;

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> customers = customerRepository.findById(id);
        return customers.isEmpty() ? null : customers.get();
    }

    @Override
    public void createCustomer(Customer customer) {
                customerRepository.save(customer);
    }
}