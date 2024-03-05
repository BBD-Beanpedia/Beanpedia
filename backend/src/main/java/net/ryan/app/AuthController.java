package net.ryan.app;

import net.ryan.error.BadRequest;
import net.ryan.model.AuthResponse;
import net.ryan.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/login")
    public AuthResponse login(BindingResult bdResult) {
        return null;
    }


}
