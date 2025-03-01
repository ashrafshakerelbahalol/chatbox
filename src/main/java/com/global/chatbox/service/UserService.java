package com.global.chatbox.service;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.global.chatbox.error.ResourceFoundException;
import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingUserRequest;
import com.global.chatbox.mapStruct.dto.UserDto;
import com.global.chatbox.mapStruct.mapper.UserMapper;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto save(AddingUserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        Optional<User> userWithTheSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithTheSameEmail.isPresent()) {
            throw new ResourceFoundException("The email is saved before");
        }
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto update(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Optional<User> existingUser = userRepository.findById(user.getId());
                
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("The user is not found");
        }

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

}
