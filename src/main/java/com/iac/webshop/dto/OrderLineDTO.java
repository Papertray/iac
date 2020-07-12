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

    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", finalOrderID=" + finalOrderID +
                ", productID=" + productID +
                '}';
    }
}
