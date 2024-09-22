package com.ChatApplicationServer.dto;


import java.util.Date;
import java.util.List;

public class ChatBoxDto {

    private Long id;
    private String name;

    private Date createdAt;
    private Date updatedOn;

    private Long participant1;

    private Long participant2;

    private List<String> userNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getParticipant1() {
        return participant1;
    }

    public void setParticipant1(Long participant1) {
        this.participant1 = participant1;
    }

    public Long getParticipant2() {
        return participant2;
    }

    public void setParticipant2(Long participant2) {
        this.participant2 = participant2;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
}
