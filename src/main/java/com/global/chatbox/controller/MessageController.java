package com.global.chatbox.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.chatbox.mapStruct.dto.MessageDto;
import com.global.chatbox.model.User;
import com.global.chatbox.response.ApiResponse;
import com.global.chatbox.service.MessageService;

import lombok.RequiredArgsConstructor;

import java.net.ResponseCache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add-message")
    public ResponseEntity<?> addMessage(@RequestParam Long userId,@RequestParam  String message, @RequestParam Long chatId) {
        try {
                  MessageDto messageDto = messageService.addMessage(userId,message,chatId);
     return ResponseEntity.ok(new ApiResponse("The message is added",messageDto)); 
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(),null)); 
        }


    }

 
    
}
