package com.iac.webshop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @RequestMapping("/abc")
    public String index() {

        return "Greetings from Spring Boot!";
    }

}
