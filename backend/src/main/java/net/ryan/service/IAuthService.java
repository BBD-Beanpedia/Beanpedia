package net.ryan.service;


import net.ryan.model.AuthResponse;

public interface IAuthService {
    AuthResponse login(String refreshToken);
}
