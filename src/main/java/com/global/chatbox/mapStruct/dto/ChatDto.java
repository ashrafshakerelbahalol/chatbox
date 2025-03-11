package com.global.chatbox.mapStruct.dto;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
 
    private Long id;

    private String topic;

    private String chatOwner;
  
    private Set <UserDtoToChat> users= new HashSet<>();
}
