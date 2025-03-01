package com.global.chatbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.chatbox.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
    
}
