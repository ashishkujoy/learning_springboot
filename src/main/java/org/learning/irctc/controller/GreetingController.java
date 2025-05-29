package org.learning.irctc.controller;

import org.learning.annotations.GetMapping;
import org.learning.annotations.RestController;

@RestController("/test")
public class GreetingController {
    @GetMapping("/greet")
    public String greet() {
        return "Hello, welcome to IRCTC!";
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Bye";
    }
}
