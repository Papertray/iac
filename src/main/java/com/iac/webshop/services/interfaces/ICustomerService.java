package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomers();

    Customer getCustomerById(long id);

    void createCustomer(Customer customer);
}
