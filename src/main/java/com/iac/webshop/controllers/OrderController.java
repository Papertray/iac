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

    @PostMapping("order/add/{finalOrderId}/product/{productId}")
    public OrderLine addToShoppingCart(@PathVariable long finalOrderId, @RequestBody OrderLine orderLine, @PathVariable long productId) {
        return orderService.addToShoppingCart(finalOrderId, orderLine, productId);
    }

    @DeleteMapping("order/delete/{orderLineId}")
    public void removeFromShoppingCart(@PathVariable long orderLineId) {
        orderService.removeFromShoppingCart(orderLineId);
    }

    @PutMapping("order/purchase/{finalOrderId}")
    public FinalOrder purchase(@PathVariable long finalOrderId) {
        return orderService.purchase(finalOrderId);
    }
}
