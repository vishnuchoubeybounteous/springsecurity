package com.example.jwt.controller;

import com.example.jwt.model.User;
import com.example.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Mono<User> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public Mono<Map<String,String>> login(@RequestBody User user) {
        return authService.login(user.getUsername(), user.getPassword())
                .map(token -> Map.of("token", token));
    }
}