package com.ChatApplicationServer.services.chat;

import com.ChatApplicationServer.dto.ChatBoxDto;
import com.ChatApplicationServer.dto.MessageDto;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.ChatBox;
import com.ChatApplicationServer.entity.Message;
import com.ChatApplicationServer.entity.User;
import com.ChatApplicationServer.enums.UserRole;
import com.ChatApplicationServer.repository.ChatBoxRepo;
import com.ChatApplicationServer.repository.MessageRepo;
import com.ChatApplicationServer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ChatBoxRepo chatBoxRepo;

    @Autowired
    private MessageRepo messageRepo;

    public List<UserDto> getAllUsers(){
        return userRepo.findAllByUserRole(UserRole.USER).stream().map(User::getUserDto).collect(Collectors.toList());
    }

    public List<UserDto> searchUsersByName(String name){
        return userRepo.findAllByNameContaining(name).stream().map(User::getUserDto).collect(Collectors.toList());
    }

    public ChatBox createChatBox(ChatBoxDto chatBoxDto) {
        User participant1 = userRepo.findById(chatBoxDto.getParticipant1())
                .orElseThrow(() -> new IllegalArgumentException("Participant1 not found with ID: " + chatBoxDto.getParticipant1()));

        User participant2 = userRepo.findById(chatBoxDto.getParticipant2()).orElseThrow(() -> new IllegalArgumentException("Participant1 not found with ID: " + chatBoxDto.getParticipant2()));

            if (participant2 != null && participant1 != null) {
                ChatBox chatBox = new ChatBox();
                if(chatBoxDto.getName() != null){
                    chatBox.setName(chatBoxDto.getName());
                }else{
                    chatBox.setName(participant1.getName() + " - " + participant2.getName());
                }
                chatBox.setCreatedAt(new Date());
                chatBox.setUpdatedOn(new Date());
                chatBox.setParticipants(Set.of(participant1, participant2));

                return chatBoxRepo.save(chatBox);
            }

            return null;

    }

    public ChatBoxDto addUserInChat(Long chatBoxId, Long userId){
        Optional<ChatBox> optionalChatBox = chatBoxRepo.findById(chatBoxId);
        Optional<User> optionalUser = userRepo.findById(userId);

        if(optionalChatBox.isPresent() && optionalUser.isPresent()){
            ChatBox chatBox = optionalChatBox.get();

            if(chatBox.getParticipants().contains(optionalUser.get())){
                throw new EntityExistsException("User already Exists.");
            }

            chatBox.getParticipants().add(optionalUser.get());
            return chatBoxRepo.save(chatBox).getDto();
        }
        throw new EntityNotFoundException("User or Chat Box not found");
    }

    public ChatBoxDto updateChatName(Long chatBoxId, String name){
        Optional<ChatBox> optionalChatBox = chatBoxRepo.findById(chatBoxId);

        if(optionalChatBox.isPresent()){
            ChatBox chatBox = optionalChatBox.get();
            chatBox.setName(name);
            return chatBoxRepo.save(chatBox).getDto();
        }
        throw new EntityNotFoundException("User or Chat Box not found");
    }

    public List<ChatBoxDto> getChatBoxesByUser(Long userId) {
       return chatBoxRepo.findAllByParticipants_Id(userId).stream().map(ChatBox::getDto).collect(Collectors.toList());
    }

    public Message createMessage(MessageDto messageDto) throws Exception {
        ChatBox chatBox = chatBoxRepo.findById(messageDto.getChatBox())
                .orElseThrow(() -> new IllegalArgumentException("Chat box not found with ID: " + messageDto.getChatBox()));

        User sender = userRepo.findById(messageDto.getSender())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found with ID: " + messageDto.getSender()));


        Message message = new Message();
        message.setChatBox(chatBox);
        message.setContent(messageDto.getContent());
        message.setSender(sender);
        message.setCreatedAt(new Date());


        return messageRepo.save(message);
    }

    public List<MessageDto> getChatBoxMessages(Long chatBoxId) {
        try{
            return messageRepo.findByChatBox_Id(chatBoxId).stream().map(Message::getDto).collect(Collectors.toList());
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

}
