package com.global.chatbox.mapStruct.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.global.chatbox.mapStruct.dto.AddingChatRequest;
import com.global.chatbox.mapStruct.dto.ChatDto;
import com.global.chatbox.mapStruct.dto.UserDtoToChat;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.User;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ChatMapper {
    @Mapping(target="userOwner.id",source="chatRequest.ownerId")
    Chat toEntity(AddingChatRequest chatRequest);

    @Mapping(target = "users", source = "users")
    @Mapping(target = "chatOwner", source = "chat.userOwner.id")
    ChatDto toDto(Chat chat);

    default Set<UserDtoToChat> mapUsers(Set<User> users) {
        if (users == null) {
            return new HashSet<>();
        }
        return users.stream()
                .map(user -> new UserDtoToChat(user.getId(), user.getLogin(), user.getEmail()))
                .collect(Collectors.toSet());
    }

}
