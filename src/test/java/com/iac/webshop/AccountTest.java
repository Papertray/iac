package com.iac.webshop;

import com.iac.webshop.helpers.Utils;
import com.iac.webshop.models.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@SpringBootTest
class AccountTest {

    @Test
    @DisplayName("Set valid email")
    void setValidEmail() {
        Account account = new Account();
        String email = "valid@email.com";
        account.setEmail(email);
        assertEquals(account.getEmail(), email);
        assertDoesNotThrow(() -> account.validateEmail());
    }

    @Test
    @DisplayName("Set invalid email")
    void setInvalidEmail() {
        Account account = new Account();
        String email = "invalidemail.com";
        account.setEmail(email);
        try {
            account.validateEmail();
            fail("Should not have validated");
        } catch(ValidationException e) {
            assertEquals(e.getMessage(), "Email is invalid");
        }
    }

    @Test
    @DisplayName("Set valid password")
    void setValidPassword() {
        Account account = new Account();
        String password = "qwerty12";

        String hashedPassword = Utils.hashPassword(password);
        account.setPassword(password);

        System.out.println(hashedPassword);


        assertEquals(hashedPassword, account.getHashedPassword());
        assertEquals(account.getPassword(), password);
        assertDoesNotThrow(() -> account.validatePassword());
    }

    @Test
    @DisplayName("Set too short password")
    void setTooShortPassword() {
        Account account = new Account();
        String password = "short";
        account.setPassword(password);
        try {
            account.validatePassword();
            fail("Should not have validated");
        } catch(ValidationException e) {
            assertEquals(e.getMessage(), "Password must be at least 8 characters long");
        }
    }

}