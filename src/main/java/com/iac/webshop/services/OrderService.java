package com.iac.webshop.services;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.repositories.IFinalOrderRepository;
import com.iac.webshop.repositories.IOrderLineRepository;
import com.iac.webshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IFinalOrderRepository finalOrderRepository;

    @Autowired
    IOrderLineRepository orderLineRepository;

    @Override
    public FinalOrder createShoppingCart(FinalOrder finalOrder) {
        return finalOrderRepository.save(finalOrder);
    }

    @Override
    public OrderLine addToShoppingCart(long finalOrderId, OrderLine orderLine) {
        Optional<FinalOrder> finalOrder = finalOrderRepository.findById(finalOrderId);
        finalOrder.ifPresent(orderLine::setFinalOrder);
        return orderLineRepository.save(orderLine);
    }

    @Override
    public FinalOrder purchase(long finalOrderId) {
        Optional<FinalOrder> finalOrder = finalOrderRepository.findById(finalOrderId);
        finalOrder.get().getOrderLines();
    }

    @Override
    public void removeFromShoppingCart(long orderLineId) {
        orderLineRepository.deleteById(orderLineId);

    }
}