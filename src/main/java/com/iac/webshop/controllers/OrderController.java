package com.iac.webshop.controllers;

import com.iac.webshop.dto.FinalOrderDTO;
import com.iac.webshop.dto.OrderLineDTO;
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

    @PostMapping("order/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FinalOrderDTO createShoppingCart(@RequestBody FinalOrder finalOrder) {
        return convertFinalOrder2DTO(orderService.createShoppingCart(finalOrder));
    }

    @PostMapping("order/add/{finalOrderId}/product/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderLineDTO addToShoppingCart(@PathVariable long finalOrderId, @RequestBody OrderLine orderLine, @PathVariable long productId) {
        return convertOrderLine2DTO(orderService.addToShoppingCart(finalOrderId, orderLine, productId));
    }

    @DeleteMapping("order/delete/{orderLineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromShoppingCart(@PathVariable long orderLineId) {
        orderService.removeFromShoppingCart(orderLineId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("order/purchase/{finalOrderId}")
    public FinalOrderDTO purchase(@PathVariable long finalOrderId) {
        FinalOrderDTO finalOrderDTO = convertFinalOrder2DTO(orderService.purchase(finalOrderId));
        activeMQSender.send(finalOrderDTO.toString());
        return finalOrderDTO;
    }

    private OrderLineDTO convertOrderLine2DTO(OrderLine orderLine) {
        return modelMapper.map(orderLine, OrderLineDTO.class);
    }

    private FinalOrderDTO convertFinalOrder2DTO(FinalOrder finalOrder) {
        return modelMapper.map(finalOrder, FinalOrderDTO.class);
    }
}
