package com.global.chatbox.mapStruct.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class AddingUserRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    private String email;
}
