package com.honesttraitor.ecommerce.modules.user.service;

import com.honesttraitor.ecommerce.modules.user.dto.UserResponseDto;
import com.honesttraitor.ecommerce.modules.user.mapper.UserMapper;
import com.honesttraitor.ecommerce.modules.user.model.User;
import com.honesttraitor.ecommerce.modules.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getCurrentUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateCurrentUser(UserResponseDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        user.setFullName(dto.getFullName());
        user.setEnabled(dto.isEnabled());

        return userMapper.toDto(userRepository.save(user));
    }
}
