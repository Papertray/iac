package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;

public interface IOrderService {
    FinalOrder createShoppingCart(FinalOrder finalOrder);

    OrderLine addToShoppingCart(long finalOrderId, OrderLine orderLine, long productId);

    FinalOrder purchase(long finalOrderId);

    void removeFromShoppingCart(long orderLineId);
}
