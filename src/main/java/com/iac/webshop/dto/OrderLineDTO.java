package com.iac.webshop.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderLineDTO {
    long id;
    LocalDateTime date;
    int amount;
    BigDecimal totalPrice;
    long finalOrderID;
    long productID;
}
