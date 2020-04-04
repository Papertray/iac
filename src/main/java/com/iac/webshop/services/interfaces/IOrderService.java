package com.iac.webshop.services.interfaces;

import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;

public interface IOrderService {
    FinalOrder createShoppingCart(FinalOrder finalOrder);

    OrderLine addToShoppingCart(FinalOrder finalOrder, OrderLine orderLine);

    OrderLine updateInShoppingCart(FinalOrder finalOrder, OrderLine orderLine);

    OrderLine removeFromShoppingCart(FinalOrder finalOrder, OrderLine orderLine);

    FinalOrder purchase(FinalOrder finalOrder);
}
