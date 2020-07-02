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
}
