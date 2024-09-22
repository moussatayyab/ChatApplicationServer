package com.ChatApplicationServer.controller;


import com.ChatApplicationServer.dto.AuthenticationRequest;
import com.ChatApplicationServer.dto.AuthenticationResponse;
import com.ChatApplicationServer.dto.SignupRequest;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.User;
import com.ChatApplicationServer.repository.UserRepository;
import com.ChatApplicationServer.services.auth.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) throws Exception {

        if (authService.hasUserWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

        UserDto createdUser = authService.createUser(signupRequest);
        if (createdUser == null)
            return new ResponseEntity<>("User not created, come again later", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = authService.login(user);
        if (dbUser == null)
            return new ResponseEntity<>("Wrong Conditionals", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return new ResponseEntity<>(authService.getUser(id), HttpStatus.OK);
    }


}
