package com.global.chatbox.mapStruct.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
 String message;
    String login;
    LocalDateTime datetime;
}
