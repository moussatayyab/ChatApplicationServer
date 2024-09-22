package com.ChatApplicationServer.services.chat;

import com.ChatApplicationServer.dto.ChatBoxDto;
import com.ChatApplicationServer.dto.MessageDto;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.ChatBox;
import com.ChatApplicationServer.entity.Message;

import java.util.List;

public interface ChatService {

    List<UserDto> getAllUsers();

    ChatBox createChatBox(ChatBoxDto chatBoxDto);

    List<ChatBoxDto> getChatBoxesByUser(Long userId);

    Message createMessage(MessageDto messageDto) throws Exception;

    List<MessageDto> getChatBoxMessages(Long chatBoxId);

    List<UserDto> searchUsersByName(String name);

    ChatBoxDto addUserInChat(Long chatBoxId, Long userId);

    ChatBoxDto updateChatName(Long chatBoxId, String name);
}
