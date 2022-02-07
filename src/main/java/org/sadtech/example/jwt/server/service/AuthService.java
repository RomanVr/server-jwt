package org.sadtech.example.jwt.server.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.sadtech.example.jwt.server.exception.AuthException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import org.sadtech.example.jwt.server.domain.JwtRequest;
import org.sadtech.example.jwt.server.domain.JwtResponse;
import org.sadtech.example.jwt.server.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }
}
