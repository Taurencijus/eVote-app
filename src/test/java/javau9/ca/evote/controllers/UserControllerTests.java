package javau9.ca.evote.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javau9.ca.evote.dto.UserDto;

import javau9.ca.evote.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllUsers_whenGetMethod() throws Exception {
        List<UserDto> users = Arrays.asList(new UserDto(), new UserDto());
        given(userService.findAllUsers()).willReturn(users);

        mockMvc.perform(get("/api/users")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(users.size())));
    }

    @Test
    public void getUserById_whenGetMethod() throws Exception {
        Long id = 1L;
        UserDto userDto = new UserDto();
        userDto.setId(id);
        given(userService.findUserById(id)).willReturn(userDto);

        mockMvc.perform(get("/api/users/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    public void updateUser_whenPutMethod() throws Exception {
        Long id = 1L;
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUserName("Updated Name");

        given(userService.updateUser(any(UserDto.class))).willAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(put("/api/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(userDto.getUserName())));
    }

    @Test
    public void deleteUser_whenDeleteMethod() throws Exception {
        Long id = 1L;

        doNothing().when(userService).deleteUser(id);

        mockMvc.perform(delete("/api/users/{id}", id))
                .andExpect(status().isNoContent());
    }
}
