package com.global.chatbox.mapStruct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoToChat {
    private Long id;
    private String login;
    private String email;  
}
