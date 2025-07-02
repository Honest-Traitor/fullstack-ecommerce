package com.honesttraitor.ecommerce.modules.auth.controller;

import com.honesttraitor.ecommerce.modules.auth.dto.AuthRequest;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthResponse;
import com.honesttraitor.ecommerce.modules.auth.dto.RegisterRequest;
import com.honesttraitor.ecommerce.modules.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
