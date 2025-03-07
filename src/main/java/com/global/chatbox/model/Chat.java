package com.global.chatbox.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="chat_topic")
    private String topic;
    @Column(name="chat_password")
    private String password;
    @ManyToOne
    @JoinColumn(name="user_chat_user_id")
    private User userOwner;

    @ManyToMany
    @JoinTable(name="user_chat",
    joinColumns={@JoinColumn(name="user_chat_chat_id")},
    inverseJoinColumns = {@JoinColumn(name="user_chat_user_id")})
    private Set <User> users= new HashSet<>();

    public void addUser(User user){
        users.add(user);
    }
    public void removeUser(User user){
        users.remove(user);
    }
}


