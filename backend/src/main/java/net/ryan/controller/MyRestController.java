package net.ryan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class MyRestController {
    @GetMapping("/greeting")
    public String greeting(@AuthenticationPrincipal Jwt principal) {

        return principal.getClaims().entrySet().stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
