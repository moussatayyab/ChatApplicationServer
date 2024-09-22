package com.ChatApplicationServer.entity;


import com.ChatApplicationServer.dto.MessageDto;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_box_id", nullable = false)
    private ChatBox chatBox;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    private String content;
    private Date createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatBox getChatBox() {
        return chatBox;
    }

    public void setChatBox(ChatBox chatBox) {
        this.chatBox = chatBox;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MessageDto getDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(id);
        messageDto.setContent(content);
        messageDto.setSender(sender.getId());
        messageDto.setSenderName(sender.getName());
        messageDto.setCreatedAt(createdAt);
        return messageDto;
    }
}
