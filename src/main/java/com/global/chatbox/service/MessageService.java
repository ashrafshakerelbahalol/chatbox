package com.global.chatbox.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.MessageDto;
import com.global.chatbox.mapStruct.mapper.MessageMapper;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.Message;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final MessageMapper messageMapper;

    public MessageDto addMessage(Long userId, String message,Long chatId) {
       Optional<User> userWithTheSameId = userService.findUserById(userId);
       if (userWithTheSameId.isEmpty())
            throw new ResourceNotFoundException("The user is not found");
        Optional<Chat> chatWithTheSameId = chatService.findChatById(userId);
        if (userWithTheSameId.isEmpty())
                throw new ResourceNotFoundException("The chat is not found");
        User currentUser = userWithTheSameId.get();
        Chat currentChat = chatWithTheSameId.get();
        Message messageToBeSaved = new Message();
        messageToBeSaved.setUser(currentUser);
        messageToBeSaved.setText(message);
        messageToBeSaved.setChat(currentChat);
        messageToBeSaved.setDatetime(LocalDateTime.now());
        messageToBeSaved =messageRepository.save(messageToBeSaved);
        return messageMapper.toDto(messageToBeSaved);
    }

}
