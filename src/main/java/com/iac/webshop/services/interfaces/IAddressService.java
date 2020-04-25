package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Address;

public interface IAddressService {
    void createAddress(Address address);
    Address getAddressById(long id);
}
