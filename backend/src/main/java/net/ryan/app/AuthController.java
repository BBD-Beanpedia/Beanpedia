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
    @PostMapping(value = "/email-login")
    public AuthResponse login(
//            @RequestBody @Validated AuthByEmailRequest request,
            BindingResult bdResult) {

//        if (bdResult.hasErrors()) {
//            throw new BadRequest(bdResult.getObjectName());
//        }
//        return /*this.authService.login(request.getEmail(), request.getPassword());*/
        throw new BadRequest(bdResult.getObjectName());
    }


}
