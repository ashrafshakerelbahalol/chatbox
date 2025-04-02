package com.global.chatbox.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.global.chatbox.model.User;
import com.global.chatbox.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByEmail(username);
        if(user.isEmpty())
          throw new UsernameNotFoundException("User is not found");
        return new UserPrinicple(user.get());
    }
    
}
