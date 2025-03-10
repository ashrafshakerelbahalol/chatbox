package com.global.chatbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.chatbox.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("select m from Message m where m.chat.id=:chatId order by m.datetime DESC")
    List<Message> findByChatIdDesc(Long chatId);
    
}
