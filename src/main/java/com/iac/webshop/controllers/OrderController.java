package com.iac.webshop.controllers;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("order/create")
    public FinalOrder createShoppingCart(@RequestBody FinalOrder finalOrder) {
        return orderService.createShoppingCart(finalOrder);
    }

    @PostMapping("order/add/{finalOrderId}")
    public OrderLine addToShoppingCart(@PathVariable long finalOrderId, @RequestBody OrderLine orderLine) {
        return orderService.addToShoppingCart(finalOrderId, orderLine);
    }

    @DeleteMapping("order/delete/{orderLineId}")
    public void removeFromShoppingCart(@PathVariable long orderLineId) {
        orderService.removeFromShoppingCart(orderLineId);
    }

    @PutMapping("order/purchase/{finalOrderId}")
    public FinalOrder purchase(@PathVariable long finalOrderId) {
        return null;
    }
}
