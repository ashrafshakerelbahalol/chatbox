package com.global.chatbox.mapStruct.dto;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;

@Getter
public class AddingMessageRequest {
    Long userId;
    String message;
    Long chatId;

}