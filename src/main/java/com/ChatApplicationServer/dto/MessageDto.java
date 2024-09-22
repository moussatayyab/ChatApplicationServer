package com.ChatApplicationServer.dto;

import java.util.Date;

public class MessageDto {

    private Long id;

    private Long chatBox;

    private Long sender;

    private String senderName;

    private String content;
    private Date createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatBox() {
        return chatBox;
    }

    public void setChatBox(Long chatBox) {
        this.chatBox = chatBox;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }


}
