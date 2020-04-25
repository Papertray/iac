package com.iac.webshop.services;

import com.iac.webshop.models.Address;
import com.iac.webshop.repositories.IAddressRepository;
import com.iac.webshop.services.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    IAddressRepository addressRepository;

    @Override
    public void createAddress(Address address) {
         addressRepository.save(address);
    }

    @Override
    public Address getAddressById(long id) {
        Optional<Address> Addresses = addressRepository.findById(id);
        return Addresses.isEmpty() ? null : Addresses.get();
    }
}
