package com.honesttraitor.ecommerce.modules.auth.controller;

import com.honesttraitor.ecommerce.modules.auth.dto.AuthRequestDto;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthResponseDto;
import com.honesttraitor.ecommerce.modules.auth.dto.RegisterRequestDto;
import com.honesttraitor.ecommerce.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(@Valid @RequestBody RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody AuthRequestDto request) {
        return authService.login(request);
    }
}
