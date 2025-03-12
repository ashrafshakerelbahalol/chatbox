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

import com.global.chatbox.mapStruct.dto.AddingChatRequest;
import com.global.chatbox.mapStruct.dto.ChatDto;
import com.global.chatbox.mapStruct.mapper.ChatMapper;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.ChatRepository;

@ExtendWith(MockitoExtension.class)
public class ChatServiceTests {
@InjectMocks
private ChatService chatService;
@Mock
private UserService userService;
@Mock
private ChatMapper chatMapper;
@Mock
private ChatRepository chatRepository;

AddingChatRequest chatRequest;
ChatDto chatDto;
Chat chat;
User user;


@BeforeEach
public void init(){
     chat= Chat.builder().id(2L)
        .topic("chatvv").password("1234").build();
     chatRequest= AddingChatRequest.builder().ownerId(1L)
       .topic("chatvv").password("1234").build();
     chatDto=ChatDto.builder()
     .chatOwner(1L)
     .id(2L).topic("chatvv").build();
     user=User.builder().email("ashrafshaker@gmail.com")
       .id(1l).login("ashraf").password("hello").build(); 
}
@Test
public void ChatService_createChat_chatCreated(){
    when(chatMapper.toEntity(chatRequest)).thenReturn(chat);
    when(chatRepository.save(chat)).thenReturn(chat);
    when(chatMapper.toDto(chat)).thenReturn(chatDto);
    ChatDto chatDtoSaved= chatService.createChat(chatRequest);
    Assertions.assertThat(chatDtoSaved).isNotNull();

}
@Test
public void ChatService_addUserToChat_userAdded(){
    when(chatRepository.findById(2L)).thenReturn(Optional.of(chat));
    when(userService.findUserById(1L)).thenReturn(Optional.of(user));
    when(chatRepository.save(chat)).thenReturn(chat);
    when(chatMapper.toDto(chat)).thenReturn(chatDto);
    ChatDto chatDtoSaved= chatService.addUserToChat(1L,2L);
    Assertions.assertThat(chatDtoSaved).isNotNull();




}

    
}
