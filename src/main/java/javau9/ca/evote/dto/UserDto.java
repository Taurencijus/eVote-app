package javau9.ca.evote.dto;

import javau9.ca.evote.models.UserType;

public class UserDto {

    private Long id;
    private String username;
    private String email;
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
