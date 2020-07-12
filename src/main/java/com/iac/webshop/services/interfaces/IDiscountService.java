package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.Discount;

import java.util.List;

public interface IDiscountService {

    List<Discount> getDiscounts();

    Discount addDiscount(Discount discount, Long productID);

}
