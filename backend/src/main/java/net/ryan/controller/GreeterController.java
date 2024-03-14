package net.ryan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreeterController {
    @GetMapping("/greeting")
    public String greeting(Authentication authentication) {

        return authentication.getName();
    }
}
