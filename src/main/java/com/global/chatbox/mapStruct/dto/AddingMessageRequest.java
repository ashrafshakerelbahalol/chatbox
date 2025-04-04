package com.global.chatbox.mapStruct.dto;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddingMessageRequest {
    Long userId;
    @NotNull
    String message;
    Long chatId;

}