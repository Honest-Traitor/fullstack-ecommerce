package com.honesttraitor.ecommerce.modules.auth.dto;

import java.util.Set;

public class RegisterRequest {

    private String email;
    private String fullName;
    private String password;
    private Set<String> roles;

    public RegisterRequest() {}

    public RegisterRequest(String email, String fullName, String password, Set<String> roles) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}