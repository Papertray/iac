package com.iac.webshop;

import com.iac.webshop.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class ProductTest {

    @Test
    @DisplayName("Set valid price")
    void setValidPrice() throws ValidationException {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("2.99");
        product.setPrice(decimal);
        assertEquals(decimal, product.getPrice());
    }

    @Test
    @DisplayName("Set too low price")
    void setTooLowPrice() {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("-1");
        assertThrows(ValidationException.class, () -> product.setPrice(decimal));
    }

    @Test
    @DisplayName("Set invalid price")
    void setInvalidPrice() {
        Product product = new Product();
        BigDecimal decimal = new BigDecimal("3");
        assertThrows(ValidationException.class, () -> product.setPrice(decimal));
    }

    @Test
    @DisplayName("Get discount price when no discounts are added")
    void getDiscountPriceWhenNoneAreAdded() {
        Product product = new Product();
        Optional<BigDecimal> discountPrice = product.getDiscountPrice();
        assertTrue(discountPrice.isEmpty());
    }

    @Test
    @DisplayName("Set name")
        void setName() {
        Product product = new Product();
        String name = "Test product 1";
        product.setName(name);
        assertEquals(name, product.getName());
    }

}