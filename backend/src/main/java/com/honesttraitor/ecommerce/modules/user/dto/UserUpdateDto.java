package com.honesttraitor.ecommerce.modules.user.dto;

public class UserUpdateDto {
    private String fullName;

    public UserUpdateDto() {}

    public UserUpdateDto(String fullName) {
        this.fullName = fullName;
    }

    // Getter & Setter

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

