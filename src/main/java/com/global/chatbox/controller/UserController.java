package com.global.chatbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.global.chatbox.error.ResourceFoundException;
import com.global.chatbox.error.ResourceNotFoundException;
import com.global.chatbox.mapStruct.dto.AddingUserRequest;
import com.global.chatbox.mapStruct.dto.LoginRequest;
import com.global.chatbox.mapStruct.dto.UserDto;
import com.global.chatbox.response.ApiResponse;
import com.global.chatbox.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid AddingUserRequest userRequest) {
     
            UserDto userdto = userService.save(userRequest);
            return ResponseEntity.ok(new ApiResponse("The user is saved", userdto));
        

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {

            String token = userService.verify(loginRequest);
            return ResponseEntity.ok(new ApiResponse("The Login process is done", token));

     

    }

    @PutMapping("update-user")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDto userDto) {
            UserDto userdto = userService.update(userDto);
            return ResponseEntity.ok(new ApiResponse("The user is updated", userdto));
    
    }
}
