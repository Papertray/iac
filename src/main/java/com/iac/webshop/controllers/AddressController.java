package com.iac.webshop.controllers;

import com.iac.webshop.models.Address;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    IAddressService addressService;
    @Autowired
    ActiveMQSender activeMQSender;

    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address)
    {
        Address newAddress = addressService.createAddress(address);
        activeMQSender.send(newAddress.toString());
        return newAddress;
    }

    @GetMapping("/addresses/{id}")
    public Address getAddressById(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

}
