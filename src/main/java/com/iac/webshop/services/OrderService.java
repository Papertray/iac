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
        if (!finalOrder.isEmpty())
            orderLine.setFinalOrder(finalOrder.get());
        return orderLineRepository.save(orderLine);
    }

    @Override
    public OrderLine updateInShoppingCart(FinalOrder finalOrder, OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    @Override
    public OrderLine removeFromShoppingCart(FinalOrder finalOrder, OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    @Override
    public FinalOrder purchase(FinalOrder finalOrder) {
        return finalOrderRepository.save(finalOrder);
    }
}