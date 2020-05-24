package com.iac.webshop;

import com.iac.webshop.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ValidationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Test
    @DisplayName("Set valid price")
    void setValidPrice() throws ValidationException {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("2.99");
        product.setPrice(decimal);
        assertEquals(product.getPrice(), decimal);
        assertDoesNotThrow(product::validatePrice);
    }

    @Test
    @DisplayName("Set too low price")
    void setTooLowPrice() {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("-1.00");
        product.setPrice(decimal);
        try {
            product.validatePrice();
            fail("Should not have validated");
        } catch(ValidationException e) {
            assertEquals(e.getMessage(), "Price was lower than minimum price");
        }
    }

    @Test
    @DisplayName("Set invalid price")
    void setInvalidPrice() {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("3");
        product.setPrice(decimal);
        try {
            product.validatePrice();
            fail("Should not have validated");
        } catch(ValidationException e) {
            assertEquals(e.getMessage(), "Price must have two decimals");
        }
    }

    @Test
    @DisplayName("Get discount price when no discounts are added")
    void getDiscountPriceWhenNoneAreAdded() {
        Product product = new Product();
        BigDecimal discountPrice = product.getDiscountPrice();
        assertSame(discountPrice, product.getPrice());
    }

    @Test
    @DisplayName("Set valid name")
        void setName() {
        Product product = new Product();
        String name = "Test product 1";
        product.setName(name);
        assertDoesNotThrow(product::validateName);
        assertEquals(name, product.getName());
    }

    @Test
    @DisplayName("Set invalid name")
    void setInvalidName() {
        Product product = new Product();
        String name = "";
        product.setName(name);
        try {
            product.validateName();
            fail("Should not have validated");
        } catch(ValidationException e) {
            assertEquals(e.getMessage(), "Name can not be empty");
        }
    }

}