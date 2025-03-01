package com.global.chatbox.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @Column(name = "message_id")
    private Long id;
    @Column(name = "message_datetime")
    private LocalDateTime datetime;
    @Lob
    @Column(name = "message_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "message_chat_id")
    private Chat chat;
    @ManyToOne
    @JoinColumn(name = "message_user_id")
    private User user;

}
