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
    @NotBlank(message = "The login is a must")
    private String login;
    @NotBlank(message = "The password is a must")
    private String password;
    @Email(message = "The email field should be a field")
    private String email;
}
