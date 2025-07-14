package com.honesttraitor.ecommerce.modules.auth.service;

import com.honesttraitor.ecommerce.modules.auth.dto.AuthRequestDto;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthResponseDto;
import com.honesttraitor.ecommerce.modules.auth.dto.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto request);
    AuthResponseDto register(RegisterRequestDto request);
}
