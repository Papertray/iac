package com.iac.webshop.services;

import com.iac.webshop.exceptions.EmptyShoppingCartException;
import com.iac.webshop.exceptions.NotFoundException;
import com.iac.webshop.exceptions.NotInStockException;
import com.iac.webshop.exceptions.OrderAlreadyBoughtException;
import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.ICustomerRepository;
import com.iac.webshop.repositories.IFinalOrderRepository;
import com.iac.webshop.repositories.IOrderLineRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    final IFinalOrderRepository finalOrderRepository;
    final IOrderLineRepository orderLineRepository;
    final IProductRepository productRepository;
    final ICustomerRepository customerRepository;

    @Override
    public FinalOrder createShoppingCart(FinalOrder finalOrder) {
        return finalOrderRepository.save(finalOrder);
    }

    @Override
    public OrderLine addToShoppingCart(long finalOrderId, OrderLine orderLine, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product", orderLine.getProduct().getId()));
        FinalOrder finalOrder = finalOrderRepository.findById(finalOrderId).orElseThrow(() -> new NotFoundException("FinalOrder", finalOrderId));

        orderLine.setFinalOrder(finalOrder);
        orderLine.setProduct(product);

        if (!hasSupply(product.getId(), orderLine.getAmount())){
            throw new NotInStockException(orderLine.getAmount(), product.getSupply(), product.getName());
        }

        orderLine.setTotalPrice();
        orderLineRepository.save(orderLine);
        finalOrder.setTotalPrice();
        finalOrderRepository.save(finalOrder);
        return orderLine;
    }

    @Override
    public FinalOrder purchase(long finalOrderId) {
        FinalOrder finalOrder = finalOrderRepository.findById(finalOrderId).orElseThrow(() -> new NotFoundException("FinalOrder", finalOrderId));
        finalOrder.purchase();
        return finalOrderRepository.save(finalOrder);
    }

    @Override
    public void removeFromShoppingCart(long orderLineId) {
        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElseThrow(() -> new NotFoundException("orderline", orderLineId));
        FinalOrder finalOrder = orderLine.getFinalOrder();
        orderLineRepository.deleteById(orderLineId);
        finalOrder.setTotalPrice();
        finalOrderRepository.save(finalOrder);
    }

    private Boolean hasSupply(long productId, int amount) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product", productId));
        return product.getSupply() >= amount;
    }



}