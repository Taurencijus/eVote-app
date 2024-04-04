package javau9.ca.evote.services;

import javau9.ca.evote.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto user);

    List<UserDto> findAllUsers();

    UserDto findUserById(Long id);

    UserDto updateUser(UserDto user);

    void deleteUser(Long id);




}
