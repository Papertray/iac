package com.iac.webshop.controllers;

import com.iac.webshop.models.Address;
import com.iac.webshop.services.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    IAddressService addressService;

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

}
