package com.iac.webshop.controllers;

import com.iac.webshop.models.Discount;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    IDiscountService discountService;
    @Autowired
    ActiveMQSender activeMQSender;

    @GetMapping("discounts")
    public List<Discount> getDiscounts() {
        return discountService.getDiscounts();
    }

    @PostMapping("discounts")
    public Discount addDiscount(@RequestBody Discount discount, Long productID) {
        Discount newDiscount = discountService.addDiscount(discount, productID);
        activeMQSender.send(newDiscount.toString());
        return newDiscount;
    }
}
