package net.ryan.TimoRefactorLater.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    @GetMapping(value = "/test")
    String test(){

        return "hello world";

    }

}
