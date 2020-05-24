package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;

public interface IOrderService {
    FinalOrder createShoppingCart(FinalOrder finalOrder);

    OrderLine addToShoppingCart(long finalOrder, OrderLine orderLine);

    FinalOrder purchase(long finalOrder);

    void removeFromShoppingCart(long orderLineId);
}
