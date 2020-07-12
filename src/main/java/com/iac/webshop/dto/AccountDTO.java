package com.iac.webshop.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountDTO {
    String email;
    Date createdOn;
    boolean isActive;

    @Override
    public String toString() {
        return "AccountDTO{" +
                "email='" + email + '\'' +
                ", createdOn=" + createdOn +
                ", isActive=" + isActive +
                '}';
    }
}
