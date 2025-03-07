package com.global.chatbox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.chatbox.error.ResourceFoundException;
import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingChatRequest;
import com.global.chatbox.mapStruct.dto.ChatDto;
import com.global.chatbox.mapStruct.mapper.ChatMapper;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final ChatMapper chatMapper;

    public ChatDto createChat(AddingChatRequest chatRequest) {
        Chat chatToBeSaved = chatMapper.toEntity(chatRequest);
        return chatMapper.toDto(chatRepository.save(chatToBeSaved));

    }

    public ChatDto addUserToChat(Long userId, Long chatId) {
        Optional<Chat> chatWithTheSameId = chatRepository.findById(chatId);
        if (chatWithTheSameId.isEmpty())
            throw new ResourceNotFoundException("The chat is not found");

        Optional<User> userWithTheSameId = userService.findUserById(userId);
        if (userWithTheSameId.isEmpty())
            throw new ResourceNotFoundException("The user is not found");

        Chat currentChat = chatWithTheSameId.get();
        User currentUser = userWithTheSameId.get();
        currentChat.addUser(currentUser);
        chatRepository.save(currentChat);
        return chatMapper.toDto(currentChat);
    }
    public Optional<Chat> findChatById(Long chatId) {
       return chatRepository.findById(chatId);
    }

}
