package com.ChatApplicationServer.controller;

import com.ChatApplicationServer.dto.ChatBoxDto;
import com.ChatApplicationServer.dto.MessageDto;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.ChatBox;
import com.ChatApplicationServer.entity.Message;
import com.ChatApplicationServer.services.chat.ChatService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/chatboxes")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllChatBoxByUser() {
        try {
            List<UserDto> allUsers = chatService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/users/name/{name}")
    public ResponseEntity<?> searchUsersByName(@PathVariable String name) {
        try {
            List<UserDto> allUsers = chatService.searchUsersByName(name);
            return ResponseEntity.status(HttpStatus.CREATED).body(allUsers);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createChatBox(@RequestBody ChatBoxDto chatBoxDto) {
        try{
            ChatBox chatBox = chatService.createChatBox(chatBoxDto);
            if (chatBox != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(chatBox);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while creating the chatbox");
        } catch (EntityExistsException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/add/{userId}/{chatBoxId}")
    public ResponseEntity<?> addUserInChat(@PathVariable Long userId, @PathVariable Long chatBoxId) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(chatService.addUserInChat(chatBoxId, userId));
        } catch (EntityNotFoundException | EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }

    }

    @GetMapping("/update/{chatBoxId}/{name}")
    public ResponseEntity<?> updateChatName(@PathVariable Long chatBoxId, @PathVariable String name) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(chatService.updateChatName(chatBoxId, name));
        } catch (EntityNotFoundException | EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatBoxDto>> getAllChatBoxByUser(@PathVariable Long userId) {
        List<ChatBoxDto> chatBox = chatService.getChatBoxesByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(chatBox);
    }

    @PostMapping("/message")
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        try {
            Message message = chatService.createMessage(messageDto);
            if (message != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(message.getDto());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An error occurred while creating the message");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while creating the message");
        }

    }

    @GetMapping("/message/{chatBoxId}")
    public ResponseEntity<List<MessageDto>> getAllChatBoxMessages(@PathVariable Long chatBoxId) {
        try {
            List<MessageDto> messageDtos = chatService.getChatBoxMessages(chatBoxId);
            return ResponseEntity.status(HttpStatus.OK).body(messageDtos);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while creating the message");
        }
    }
}
