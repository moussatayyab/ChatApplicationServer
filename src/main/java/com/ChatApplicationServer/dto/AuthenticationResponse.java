package com.ChatApplicationServer.dto;

import com.ChatApplicationServer.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;


}
