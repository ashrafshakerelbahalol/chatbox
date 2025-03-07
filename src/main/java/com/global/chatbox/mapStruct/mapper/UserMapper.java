package com.global.chatbox.mapStruct.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.global.chatbox.mapStruct.dto.AddingUserRequest;
import com.global.chatbox.mapStruct.dto.ChatDtoToUser;
import com.global.chatbox.mapStruct.dto.UserDto;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.User;

@Mapper(componentModel ="spring",uses = ChatMapper.class)
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping( target = "chats",source = "chats")
    User toEntity(UserDto userDto);
    User toEntity(AddingUserRequest userRequest);
    default Set<ChatDtoToUser> mapChats(Set<Chat> chats) {
        if (chats == null) {
            return new HashSet<>();
        }
        return chats.stream()
                .map(chat -> new ChatDtoToUser(chat.getId(), chat.getTopic()))
                .collect(Collectors.toSet());
    }
}
