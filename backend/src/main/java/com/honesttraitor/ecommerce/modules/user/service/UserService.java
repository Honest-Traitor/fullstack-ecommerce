package com.honesttraitor.ecommerce.modules.user.service;

import com.honesttraitor.ecommerce.modules.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getCurrentUserProfile();
    UserResponseDto updateCurrentUser(UserResponseDto dto);
}
