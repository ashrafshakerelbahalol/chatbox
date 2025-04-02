package com.global.chatbox.error;

public class InvalidUserInputException extends RuntimeException {
    public InvalidUserInputException(String message){
        super(message);
    }
    
}
