package com.honesttraitor.ecommerce.modules.user.dto;

import java.util.Set;

public class UserResponseDto {

    private Long id;
    private String email;
    private String fullName;
    private Set<String> roles;
    private boolean enabled;

    public UserResponseDto() {}

    public UserResponseDto(Long id, String email, String fullName, Set<String> roles, boolean enabled) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
        this.enabled = enabled;
    }

    // Getters and setters ↓

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
