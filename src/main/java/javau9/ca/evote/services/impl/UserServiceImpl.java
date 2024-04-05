package javau9.ca.evote.services.impl;

import javau9.ca.evote.dto.UserDto;
import javau9.ca.evote.exceptions.EntityNotFoundException;
import javau9.ca.evote.models.User;
import javau9.ca.evote.repositories.UserRepository;
import javau9.ca.evote.services.UserService;
import javau9.ca.evote.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /*@Override
    public UserDto registerUser(UserDto userDto) {
        User user = MapperUtils.convertToUserEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return MapperUtils.convertToUserDto(savedUser);
    }*/

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(MapperUtils::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return MapperUtils.convertToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userDto.getId()));
        User userToUpdate = MapperUtils.convertToUserEntity(userDto);
        userToUpdate.setPassword(existingUser.getPassword());
        User updatedUser = userRepository.save(userToUpdate);
        return MapperUtils.convertToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
