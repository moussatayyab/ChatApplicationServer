package com.ChatApplicationServer.entity;

import com.ChatApplicationServer.dto.ChatBoxDto;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "chat_box")
@Data
public class ChatBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedOn;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_box_users",
            joinColumns = @JoinColumn(name = "chat_box_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }


    public ChatBoxDto getDto(){
        ChatBoxDto chatBoxDto = new ChatBoxDto();
        chatBoxDto.setName(name);
        chatBoxDto.setId(id);
        chatBoxDto.setUpdatedOn(updatedOn);
        chatBoxDto.setUserNames(participants.stream().map(User::getName).collect(Collectors.toList()));
        return chatBoxDto;
    }
}
