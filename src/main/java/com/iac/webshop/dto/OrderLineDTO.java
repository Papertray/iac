package com.iac.webshop.dto;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class OrderLineDTO {
    long id;
    LocalDateTime date;
    int amount;
    BigDecimal totalPrice;
    int finalOrderID;
    int productID;
}
