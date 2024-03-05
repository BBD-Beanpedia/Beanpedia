package net.ryan.app;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRestController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    class User {
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long user) {
        // ...
        return new User();
    }


    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user) {
        // ...
        return new User();
    }

}
