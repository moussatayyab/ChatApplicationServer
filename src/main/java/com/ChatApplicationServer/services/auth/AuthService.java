package com.ChatApplicationServer.services.auth;


import com.ChatApplicationServer.dto.SignupRequest;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.User;

public interface AuthService {

     UserDto createUser(SignupRequest signupRequest) throws Exception;

     Boolean hasUserWithEmail(String email);

     User login(User user);

     User getUser(Long userId);

}
