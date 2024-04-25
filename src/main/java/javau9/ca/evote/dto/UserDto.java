package javau9.ca.evote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import javau9.ca.evote.models.UserType;

public class UserDto {

    private Long id;
    @NotBlank(message = "User Name is mandatory")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only use letters, capital letters, numbers")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email has to be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private UserType type;

    public UserDto() {}

    public UserDto(Long id, String username, String email, UserType type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
