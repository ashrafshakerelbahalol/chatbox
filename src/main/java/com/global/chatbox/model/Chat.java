package com.global.chatbox.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="chat")
public class Chat {
    @Id
    @Column(name="chat_id")
    private Long id;
    @Column(name="chat_topic")
    private String topic;
    @Column(name="chat_password")
    private String password;
    @ManyToMany
    @JoinTable(name="user_chat",
    joinColumns={@JoinColumn(name="user_chat_chat_id")},
    inverseJoinColumns = {@JoinColumn(name="user_chat_user_id")})
    private Set <User> users= new HashSet<>();

}


