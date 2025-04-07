package com.global.chatbox.repository;

import java.lang.module.ModuleDescriptor.Builder;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.global.chatbox.model.Chat;
import com.global.chatbox.model.Message;
import com.global.chatbox.model.User;

@DataJpaTest
@ActiveProfiles("test")
public class MessageRepositoryTest {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;

@Test
public void messageRepository_findByChatIdDesc_returnListOfMessage(){
    //Arrange
    User user = new User();
    user.setEmail("ashrafShaker@gmail.com");
    user.setPassword("123456");
    user.setLogin("ashraf");
    user=userRepository.save(user);
    Chat chat=Chat.builder().topic("hello").password("123").userOwner(user).build();
    chat.addUser(user);
    chat=chatRepository.save(chat);
    Message message1 =Message.builder().chat(chat).text("hello").user(user).datetime(LocalDateTime.of(2016, 12, 1, 1, 25, 0, 0)).build(); 
    Message message2 =Message.builder().chat(chat).text("hello").user(user).datetime(LocalDateTime.of(2015, 12, 1, 1, 25, 0, 0)).build(); 
    messageRepository.save(message1);
    messageRepository.save(message2);


    //Act
    List<Message>messages=messageRepository.findByChatIdDesc(chat.getId());
    //Assert
    assertThat(messages).hasSize(2);
    assertThat(messages.get(0).getDatetime()).isEqualTo(LocalDateTime.of(2016, 12, 1, 1, 25, 0, 0));
    
}
}
