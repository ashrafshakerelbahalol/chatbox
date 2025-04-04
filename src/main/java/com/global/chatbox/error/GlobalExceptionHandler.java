package com.global.chatbox.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.global.chatbox.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(InvalidUserInputException.class)
 public ResponseEntity<ApiResponse> handleInvalidUserException( InvalidUserInputException e){
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
 }
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<ApiResponse> handleResourceNotFoundException( ResourceNotFoundException e){
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
 }
 @ExceptionHandler(ResourceFoundException.class)
 public ResponseEntity<ApiResponse> handleIResourceFoundException( ResourceFoundException e){
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
 }
 @ExceptionHandler(RuntimeException.class)
 public ResponseEntity<ApiResponse> handleIRuntimeException( RuntimeException e){
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
 }
 
}
