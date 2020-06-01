package com.iac.webshop.services;

import com.iac.webshop.exceptions.NotFoundException;
import com.iac.webshop.exceptions.NotInStockException;
import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.models.Product;
import com.iac.webshop.repositories.IFinalOrderRepository;
import com.iac.webshop.repositories.IOrderLineRepository;
import com.iac.webshop.repositories.IProductRepository;
import com.iac.webshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IFinalOrderRepository finalOrderRepository;

    @Autowired
    IOrderLineRepository orderLineRepository;

    @Autowired
    IProductRepository productRepository;

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
        orderLine.setTotalPrice(calculateTotalPrice(product, orderLine.getAmount()));

        if (!hasSupply(product.getId(), orderLine.getAmount())){
            throw new NotInStockException(orderLine.getAmount(), product.getSupply(), product.getName());
        }

        return orderLineRepository.save(orderLine);
    }

    @Override
    public Boolean purchase(long finalOrderId) {
        FinalOrder finalOrder = finalOrderRepository.findById(finalOrderId).orElseThrow(() -> new NotFoundException("FinalOrder", finalOrderId));
        finalOrder.isFinished();

        Set<OrderLine> orderLines = finalOrder.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            if (!hasSupply(orderLine.getProduct().getId(), orderLine.getAmount())){
                throw new NotInStockException(orderLine.getAmount(), orderLine.getProduct().getSupply(), orderLine.getProduct().getName());
            }
        }
        finalOrder.setFinished(true);
        return true;
    }

    @Override
    public void removeFromShoppingCart(long orderLineId) {
        orderLineRepository.deleteById(orderLineId);

    }

    private BigDecimal calculateTotalPrice(Product product, long amount){
        return product.getDiscountPrice().multiply(BigDecimal.valueOf(amount));
    }

    private Boolean hasSupply(long productId, int amount) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product", productId));
        return product.getSupply() >= amount;
    }
}