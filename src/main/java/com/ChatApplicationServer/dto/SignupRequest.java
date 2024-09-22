package com.ChatApplicationServer.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;

    private String password;

    private String name;

    private String type;

}
