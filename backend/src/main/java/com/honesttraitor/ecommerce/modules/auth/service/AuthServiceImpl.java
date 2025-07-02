package com.honesttraitor.ecommerce.modules.auth.service;

import com.honesttraitor.ecommerce.common.exception.InvalidCredentialException;
import com.honesttraitor.ecommerce.common.exception.UsernameAlreadyExistsException;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthRequest;
import com.honesttraitor.ecommerce.modules.auth.dto.AuthResponse;
import com.honesttraitor.ecommerce.modules.auth.dto.RegisterRequest;
import com.honesttraitor.ecommerce.modules.auth.jwt.JwtUtil;
import com.honesttraitor.ecommerce.modules.user.model.User;
import com.honesttraitor.ecommerce.modules.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        Set<String> roles = request.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = Collections.singleton("USER");
        }
        User user = new User(
                null,
                request.getEmail(),
                request.getFullName(),
                passwordEncoder.encode(request.getPassword()),
                roles,
                true
        );
        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .email(user.getEmail())
                .roles(user.getRoles())
                .refreshToken(null)
                .build();
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("Invalid/Unregistered username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Invalid password");
        }

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .email(user.getEmail())
                .roles(user.getRoles())
                .refreshToken(null)
                .build();
    }
}
