package com.global.chatbox.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.global.chatbox.mapStruct.dto.AddingUserRequest;
import com.global.chatbox.mapStruct.dto.UserDto;
import com.global.chatbox.mapStruct.mapper.UserMapper;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    User user;
    UserDto userDto;
    AddingUserRequest userRequest;

    @BeforeEach
    void Init() {
        user = User.builder().email("ashrafShaker@gmail.com").id(1L)
                .login("ashraf").password("1234").build();

        userDto = UserDto.builder().email("ashrafShaker@gmail.com")
                .login("ashraf").id(1L).build();

        userRequest = AddingUserRequest.builder().email("ashrafShaker@gmail.com")
                .login("ashraf").build();
    }

    @Test
    public void UserService_Save_ReturnUserDto() {
        when(userRepository.findByEmail("ashrafShaker@gmail.com")).thenReturn(Optional.empty());
        when(userMapper.toEntity(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        UserDto savedUserDto= userService.save(userRequest);
        Assertions.assertThat(savedUserDto).isNotNull();
    }

    @Test
    public void userService_Update(){
        when(userRepository.findById(userDto.getId())).thenReturn(Optional.of(user));
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        UserDto updateUserDto= userService.update(userDto);
        Assertions.assertThat(updateUserDto).isNotNull();
    }
    @Test
    public void UserService_FindUserById(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> UserWithTheId= userService.findUserById(1L);
        Assertions.assertThat(UserWithTheId).isNotNull();
        Assertions.assertThat(UserWithTheId).isEqualTo(Optional.of(user));

    }

}
