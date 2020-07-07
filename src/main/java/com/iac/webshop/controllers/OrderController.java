package com.iac.webshop.controllers;

import com.iac.webshop.dto.FinalOrderDTO;
import com.iac.webshop.dto.OrderLineDTO;
import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.services.interfaces.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("order/create")
    public FinalOrderDTO createShoppingCart(@RequestBody FinalOrder finalOrder) {
        return convertFinalOrder2DTO(orderService.createShoppingCart(finalOrder));
    }

    @PostMapping("order/add/{finalOrderId}/product/{productId}")
    public OrderLineDTO addToShoppingCart(@PathVariable long finalOrderId, @RequestBody OrderLine orderLine, @PathVariable long productId) {
        return convertOrderLine2DTO(orderService.addToShoppingCart(finalOrderId, orderLine, productId));
    }

    @DeleteMapping("order/delete/{orderLineId}")
    public void removeFromShoppingCart(@PathVariable long orderLineId) {
        orderService.removeFromShoppingCart(orderLineId);
    }

    @PutMapping("order/purchase/{finalOrderId}")
    public FinalOrderDTO purchase(@PathVariable long finalOrderId) {
        return convertFinalOrder2DTO(orderService.purchase(finalOrderId));
    }

    private OrderLineDTO convertOrderLine2DTO(OrderLine orderLine) {
        return modelMapper.map(orderLine, OrderLineDTO.class);
    }

    private FinalOrderDTO convertFinalOrder2DTO(FinalOrder finalOrder) {
        return modelMapper.map(finalOrder, FinalOrderDTO.class);
    }

    private OrderLine convertOrderLineDTO2Entity(OrderLineDTO orderLineDTO) {
        return modelMapper.map(orderLineDTO, OrderLine.class);
    }

    private FinalOrder ConvertFinalOrderDTO2Entity(FinalOrderDTO finalOrderDTO) {
        return modelMapper.map(finalOrderDTO, FinalOrder.class);
    }

}
