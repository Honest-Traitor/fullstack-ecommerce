package com.honesttraitor.ecommerce.modules.auth.dto;

import java.util.Set;

public class AuthResponseDto {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Set<String> roles;

    public AuthResponseDto() {}

    public AuthResponseDto(String accessToken, String refreshToken, String email, Set<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.roles = roles;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public static class AuthResponseBuilder {
        private String accessToken;
        private String refreshToken;
        private String email;
        private Set<String> roles;

        public AuthResponseBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AuthResponseBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AuthResponseBuilder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public AuthResponseDto build() {
            return new AuthResponseDto(accessToken, refreshToken, email, roles);
        }
    }
}