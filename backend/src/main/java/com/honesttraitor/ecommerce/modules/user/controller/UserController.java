package com.honesttraitor.ecommerce.modules.user.controller;

import com.honesttraitor.ecommerce.modules.user.dto.UserResponseDto;
import com.honesttraitor.ecommerce.modules.user.dto.UserUpdateDto;
import com.honesttraitor.ecommerce.modules.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🧑‍💼 Admin-only: Fetch all user profiles
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🙋 Current user: View own profile
    @GetMapping("/me")
    public UserResponseDto getCurrentUser() {
        return userService.getCurrentUserProfile();
    }

    // 📝 Current user: Update own info
    @PutMapping("/me")
    public UserResponseDto updateProfile(@Valid @RequestBody UserUpdateDto dto) {
        return userService.updateCurrentUser(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
