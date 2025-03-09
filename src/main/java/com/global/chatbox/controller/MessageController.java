package com.global.chatbox.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.chatbox.error.ResourceFoundException;
import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingMessageRequest;
import com.global.chatbox.mapStruct.dto.MessageDto;
import com.global.chatbox.model.User;
import com.global.chatbox.response.ApiResponse;
import com.global.chatbox.service.MessageService;

import lombok.RequiredArgsConstructor;

import java.net.ResponseCache;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add-message")
    public ResponseEntity<?> addMessage(@RequestBody AddingMessageRequest messageRequest) {
        try {
            MessageDto messageDto = messageService.addMessage(messageRequest.getUserId(),messageRequest.getMessage(), messageRequest.getChatId());
            return ResponseEntity.ok(new ApiResponse("The message is added", messageDto));
        }  catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));

        }

    }
 
    @GetMapping("/get-messages-by-chat-id/{chatId}")
    public ResponseEntity<?> getMessagesByChatId(@PathVariable long chatId) {
        try {
            List<MessageDto> messageDtos = messageService.getMessageById( chatId);
            return ResponseEntity.ok(new ApiResponse("The message is added", messageDtos));
        
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));

        }

 
    }

}
