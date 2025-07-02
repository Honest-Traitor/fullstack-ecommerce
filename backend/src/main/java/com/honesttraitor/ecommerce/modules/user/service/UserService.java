package com.honesttraitor.ecommerce.modules.user.service;

import com.honesttraitor.ecommerce.modules.user.dto.UserResponseDto;
import com.honesttraitor.ecommerce.modules.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getCurrentUserProfile();
    UserResponseDto updateCurrentUser(UserUpdateDto dto);
    void delete(Long id);
}
