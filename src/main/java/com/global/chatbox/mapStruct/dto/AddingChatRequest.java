package com.global.chatbox.mapStruct.dto;

import java.util.HashSet;
import java.util.Set;

import com.global.chatbox.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class AddingChatRequest {
    private String topic;
    private String password;
    private String userOwner;

}
