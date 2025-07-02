package com.honesttraitor.ecommerce.modules.auth.service;

import com.honesttraitor.ecommerce.modules.auth.dto.AuthRequest;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthResponse;
import com.honesttraitor.ecommerce.modules.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
}
