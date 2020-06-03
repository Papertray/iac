package com.iac.webshop.mappers;

import com.iac.webshop.dto.OrderLineDTO;
import com.iac.webshop.models.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    // link tussen orderline & orderlineDTO

    public OrderLine map(OrderLineDTO orderLineDTO){return null;};
    public OrderLineDTO reverse(OrderLine orderLine){return null;};
}
