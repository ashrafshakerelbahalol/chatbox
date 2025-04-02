package com.global.chatbox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.chatbox.error.InvalidUserInputException;
import com.global.chatbox.error.ResourceFoundException;
import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingChatRequest;
import com.global.chatbox.mapStruct.dto.ChatDto;
import com.global.chatbox.mapStruct.mapper.ChatMapper;
import com.global.chatbox.model.Chat;
import com.global.chatbox.model.User;
import com.global.chatbox.repository.ChatRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final ChatMapper chatMapper;

    public ChatDto createChat(AddingChatRequest chatRequest) {
        userService.findUserById(chatRequest.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Chat chatToBeSaved = chatMapper.toEntity(chatRequest);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        chatToBeSaved.setPassword(encoder.encode(chatRequest.getPassword()));
        return chatMapper.toDto(chatRepository.save(chatToBeSaved));

    }

    public ChatDto addUserToChat(Long userId, Long chatId, String password) {
        Optional<Chat> chatWithTheSameId = chatRepository.findById(chatId);
        if (chatWithTheSameId.isEmpty())
            throw new ResourceNotFoundException("The chat is not found");

        Optional<User> userWithTheSameId = userService.findUserById(userId);
        if (userWithTheSameId.isEmpty())
            throw new ResourceNotFoundException("The user is not found");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
      
        Chat currentChat = chatWithTheSameId.get();
        User currentUser = userWithTheSameId.get();
        if ( !encoder.matches(password, currentChat.getPassword())) {
            System.out.println(currentChat.getPassword());
            System.out.println(password);
            throw new InvalidUserInputException("The password is not correct");
        }
        currentChat.addUser(currentUser);
        currentChat=chatRepository.save(currentChat);
        return chatMapper.toDto(currentChat);

    }

    public Optional<Chat> findChatById(Long chatId) {
        return chatRepository.findById(chatId);
    }

}
