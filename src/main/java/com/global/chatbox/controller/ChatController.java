package com.global.chatbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingChatRequest;
import com.global.chatbox.mapStruct.dto.ChatDto;
import com.global.chatbox.response.ApiResponse;
import com.global.chatbox.service.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("create-chat")
    public ResponseEntity<?> createChat(@RequestBody AddingChatRequest chatRequest) {
        try {
            ChatDto chatDto = chatService.createChat(chatRequest);
            return ResponseEntity.ok(new ApiResponse("The chat is created ", chatDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("{user-id}/add-user-to-chat/{chat-id}")
    public ResponseEntity<?> addUserToChat(
            @PathVariable("user-id") Long userId,
             @PathVariable("chat-id") Long chatId,@RequestBody String password) {
        try {
            ChatDto chatDto= chatService.addUserToChat(userId, chatId, password);
            return ResponseEntity.ok(new ApiResponse("The user is added to the chat",chatDto));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));

        }catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
