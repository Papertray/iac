package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;

public interface IOrderService {
    FinalOrder createShoppingCart(FinalOrder finalOrder);

    OrderLine addToShoppingCart(long finalOrderId, OrderLine orderLine, long productId);

    Boolean purchase(long finalOrder);

    void removeFromShoppingCart(long orderLineId);
}
