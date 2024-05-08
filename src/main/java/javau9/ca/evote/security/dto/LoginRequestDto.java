package javau9.ca.evote.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import javau9.ca.evote.models.UserType;

public class LoginRequestDto {

    @NotBlank(message = "User Name is mandatory")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only use letters, capital letters, numbers")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private UserType type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
