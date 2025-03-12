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
    @JoinColumn(name="chat_owner_user_id")
    private User userOwner;
    @Builder.Default 
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


