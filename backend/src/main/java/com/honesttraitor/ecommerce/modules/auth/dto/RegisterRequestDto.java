package com.honesttraitor.ecommerce.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class RegisterRequestDto {

    @NotBlank(message = "Email cannot be blank") // Email must not be null or empty
    @Email(message = "Email should be valid") // Must be a well-formed email address
    private String email;

    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain " +
                    "at least one digit, " +
                    "one lowercase letter, " +
                    "one uppercase letter, " +
                    "one special character (@#$%^&+=!), " +
                    "and no whitespace")
//             ^: Start of the string.
//            (?=.*[0-9]): Positive lookahead to ensure at least one digit (0-9).
//            (?=.*[a-z]): Positive lookahead to ensure at least one lowercase letter (a-z).
//            (?=.*[A-Z]): Positive lookahead to ensure at least one uppercase letter (A-Z).
//            (?=.*[@#$%^&+=!]): Positive lookahead to ensure at least one of the specified special characters. You can customize this set.
//            (?=\\S+$): Positive lookahead to ensure no whitespace characters (\S+ matches one or more non-whitespace characters, and $ signifies the end of the string, effectively ensuring no whitespace throughout).
//            .{8,}: Matches any character (except newline) at least 8 times. This is redundant if @Size(min = 8) is already used, but good for self-contained regex. The .{8,}$ part often handles the minimum length within the regex itself.
    private String password;

    // Roles can be null or empty if default "USER" is assigned by service
    private Set<String> roles;

    public RegisterRequestDto() {}

    public RegisterRequestDto(String email, String fullName, String password, Set<String> roles) {
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