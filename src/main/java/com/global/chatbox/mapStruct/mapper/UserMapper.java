package com.global.chatbox.mapStruct.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.global.chatbox.mapStruct.dto.AddingUserRequest;
import com.global.chatbox.mapStruct.dto.UserDto;
import com.global.chatbox.model.User;

@Mapper(componentModel ="spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toEntity(AddingUserRequest userRequest);
    
}
