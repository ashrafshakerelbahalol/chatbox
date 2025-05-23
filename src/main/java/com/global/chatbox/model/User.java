package com.global.chatbox.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.generator.Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name="users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_login",unique=true)
    private String login;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email", unique = true)
    private String email;
    @ManyToMany(mappedBy = "users") 
    private Set<Chat> chats = new HashSet<>();

}
