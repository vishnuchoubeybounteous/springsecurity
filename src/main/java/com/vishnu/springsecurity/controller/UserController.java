package com.example.jwt.controller;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;



    // USER role only
    @GetMapping("/user/profile")
    public Mono<Map<String, String>> profile(Authentication authentication) {

        String username = authentication.getName();
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        return Mono.just(
                Map.of(
                        "username", username,
                        "role", role
                )
        );
    }

    // ADMIN role only
    @GetMapping("/admin/users")
    public Flux<?> getAllUsers() {
        return userRepository.findAll();
    }
}