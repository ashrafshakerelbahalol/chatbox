package com.global.chatbox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.chatbox.model.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    
}
