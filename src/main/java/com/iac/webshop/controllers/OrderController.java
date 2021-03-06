package com.iac.webshop.controllers;

import com.iac.webshop.dto.FinalOrderDTO;
import com.iac.webshop.dto.OrderLineDTO;
import com.iac.webshop.helpers.Utils;
import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.services.ActiveMQSender;
import com.iac.webshop.services.interfaces.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    ActiveMQSender activeMQSender;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("orders/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FinalOrderDTO createShoppingCart(@RequestBody FinalOrder finalOrder) {
        FinalOrderDTO finalOrderDTO = Utils.convertToDto(orderService.createShoppingCart(finalOrder), FinalOrderDTO.class);
        activeMQSender.send(finalOrderDTO.toString());
        return finalOrderDTO;
    }

    @PostMapping("orders/add/{finalOrderId}/product/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderLineDTO addToShoppingCart(@PathVariable long finalOrderId, @RequestBody OrderLine orderLine, @PathVariable long productId) {
        OrderLineDTO orderLineDTO = Utils.convertToDto(orderService.addToShoppingCart(finalOrderId, orderLine, productId), OrderLineDTO.class);
        activeMQSender.send(orderLineDTO.toString());
        return orderLineDTO;
    }

    @DeleteMapping("orders/delete/{orderLineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromShoppingCart(@PathVariable long orderLineId) {
        orderService.removeFromShoppingCart(orderLineId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("orders/purchase/{finalOrderId}")
    public FinalOrderDTO purchase(@PathVariable long finalOrderId) {
        FinalOrderDTO finalOrderDTO = Utils.convertToDto(orderService.purchase(finalOrderId), FinalOrderDTO.class);
        activeMQSender.send(finalOrderDTO.toString());
        return finalOrderDTO;
    }
}
