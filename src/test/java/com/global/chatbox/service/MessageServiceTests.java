package com.global.chatbox.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.global.chatbox.mapStruct.dto.MessageDto;
import com.global.chatbox.mapStruct.mapper.MessageMapper;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.Message;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTests {
    @InjectMocks
    private MessageService messageService;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserService userService;
    @Mock
    private ChatService chatService;
    @Mock
    private MessageMapper messageMapper;

    Chat chat;
    User user;
    Message message1;
    Message message2;
    Long userId;
    Long chatId;
    String text;
     MessageDto messageDto;

    @BeforeEach
    public void Init() { 
        userId=1l;
        text="hello";
        chatId=2l;
        user=User.builder().email("ashrafshaker@gmail.com")
        .id(userId).login("ashraf").password("hello").build(); 
        chat= Chat.builder().id(2L)
        .topic("chatvv").password("1234").build();
        message1 =Message.builder().chat(chat).text(text).user(user).datetime(LocalDateTime.now()).build(); 
        message2 =Message.builder().chat(chat).text("hello").user(user).datetime(LocalDateTime.of(2015, 12, 1, 1, 25, 0, 0)).build(); 
        messageDto=MessageDto.builder().datetime(LocalDateTime.now())
           .login(user.getLogin()).message(text).build();

    }
    @Test
    public void MessageService_AddMessage_MessageCreated(){
         when(userService.findUserById(userId)).thenReturn(Optional.of(user));
         when(chatService.findChatById(chatId)).thenReturn(Optional.of(chat));
         when(messageRepository.save(Mockito.any(Message.class))).thenReturn(message1);
         when(messageMapper.toDto(message1)).thenReturn(messageDto);
         MessageDto messageDto = messageService.addMessage(userId,text,2L);
         Assertions.assertThat(messageDto).isNotNull();
    
        }

        @Test
        public void MessageService_GetMessageById_ReturnListOfMessageDesc(){
             when(messageRepository.findByChatIdDesc(2L)).thenReturn(List.of(message1,message2));
             when(chatService.findChatById(chatId)).thenReturn(Optional.of(chat));
             when(messageMapper.toDto(any(Message.class))).thenReturn(new MessageDto());
             List<MessageDto>  chatMessages=messageService.getMessageById(2L);
             Assertions.assertThat(chatMessages).hasSize(2);
             
        }


}
