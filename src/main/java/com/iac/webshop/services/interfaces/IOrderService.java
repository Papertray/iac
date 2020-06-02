package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    FinalOrder createShoppingCart(FinalOrder finalOrder);

    OrderLine addToShoppingCart(long finalOrderId, OrderLine orderLine, long productId);

    ResponseEntity purchase(long finalOrder);

    void removeFromShoppingCart(long orderLineId);
}
