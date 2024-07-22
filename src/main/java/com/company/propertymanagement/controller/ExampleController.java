package com.company.propertymanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/v1")
public class ExampleController {

    @GetMapping("/sayhello")
    public String sayHello() {
        return "Hello!";
    }
}
