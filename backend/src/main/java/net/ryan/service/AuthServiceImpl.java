package net.ryan.service;

import net.ryan.model.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Override
    public AuthResponse login(String email, String password) {
        return null;
    }

    @Override
    public AuthResponse login(String refreshToken) {
        return null;
    }
}
