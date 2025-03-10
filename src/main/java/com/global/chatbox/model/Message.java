package com.global.chatbox.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", chat=" + chat +
                ", user=" + user +
                '}';
    }

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
