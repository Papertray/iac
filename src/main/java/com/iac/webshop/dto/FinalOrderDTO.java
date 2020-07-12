package com.iac.webshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FinalOrderDTO {
    long id;
    LocalDateTime date;
    BigDecimal totalPrice;
    boolean finished;

    @Override
    public String toString() {
        return "FinalOrderDTO{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", finished=" + finished +
                '}';
    }

}


