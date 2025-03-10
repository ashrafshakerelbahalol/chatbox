package com.global.chatbox.mapStruct.dto;

import java.util.HashSet;
import java.util.Set;

import com.global.chatbox.model.Chat;
import com.global.chatbox.model.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String email;
    private Set<ChatDtoToUser> chats = new HashSet<>();
}
