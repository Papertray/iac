package com.iac.webshop;

import com.iac.webshop.helpers.Utils;
import com.iac.webshop.models.Account;
import com.iac.webshop.models.FinalOrder;
import com.iac.webshop.models.OrderLine;
import com.iac.webshop.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
class OrderTest {

    @Test
    @DisplayName("Add an orderline")
    void addOrdeline() throws ValidationException {
        Product product = createTestProduct("Mondkapje", 3, 2.99);
        FinalOrder finalOrder = new FinalOrder();
        OrderLine orderLine = createTestOrderLine(product, 1);

        finalOrder.addOrderLine(orderLine);
        assertTrue(finalOrder.getOrderLines().size() == 1);
    }

    @Test
    @DisplayName("Remove an orderline")
    void removeAnOrderline() throws ValidationException {
        Product product1 = createTestProduct("Product 1", 100, 2.99);
        Product product2 = createTestProduct("Product 2", 100, 2.99);
        Product product3 = createTestProduct("Product 3", 100, 2.99);

        FinalOrder finalOrder = new FinalOrder();
        finalOrder.addOrderLine(createTestOrderLine(product1, 1));
        finalOrder.addOrderLine(createTestOrderLine(product2, 1));
        OrderLine orderLineToBeRemoved = createTestOrderLine(product3, 1);
        finalOrder.addOrderLine(orderLineToBeRemoved);

        assertTrue(finalOrder.getOrderLines().size() == 3);

        finalOrder.removeOrderLine(orderLineToBeRemoved);
        assertTrue(finalOrder.getOrderLines().size() == 2);
        assertFalse(finalOrder.getOrderLines().contains(orderLineToBeRemoved));
    }

    @Test
    @DisplayName("Get total price")
    void getTotalPrice() throws ValidationException {
        double product1Price = 0.99;
        int product1Amount = 3;
        Product product1 = createTestProduct("Product 1", 100, product1Price);
        Product product2 = createTestProduct("Product 2", 100, 0.59);
        FinalOrder finalOrder = new FinalOrder();
        OrderLine orderLine1 = createTestOrderLine(product1, product1Amount);
        BigDecimal product1TotalPrice = BigDecimal.valueOf(product1Price).multiply(BigDecimal.valueOf(product1Amount));

        // Check if total price of orderline works correctly
        assertEquals(orderLine1.getTotalPrice(), product1TotalPrice);

        OrderLine orderLine2 = createTestOrderLine(product2, 5);
        finalOrder.addOrderLine(orderLine1);
        finalOrder.addOrderLine(orderLine2);

        BigDecimal totalPrice = orderLine1.getTotalPrice().add(orderLine2.getTotalPrice());
        // Check if total price of final order works correctly
        assertEquals(finalOrder.getTotalPrice(), totalPrice);
    }

    OrderLine createTestOrderLine(Product product, int amount) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(new Random().nextInt());
        orderLine.setProduct(product);
        orderLine.setAmount(amount);
        orderLine.setTotalPrice();
        return orderLine;
    }

    Product createTestProduct(String name, int supply, double price) {
        Product product = new Product();
        product.setId(new Random().nextInt());
        product.setPrice(BigDecimal.valueOf(price));
        product.setName(name);
        product.setSupply(supply);
        return product;
    }

}