package com.global.chatbox.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.chatbox.model.User;

public interface UserRepository  extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);
    
}
