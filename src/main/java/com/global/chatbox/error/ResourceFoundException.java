package com.global.chatbox.error;

public class ResourceFoundException extends RuntimeException {
    public ResourceFoundException (String message){
        super(message);
    }
}
