package com.ChatApplicationServer.repository;

import com.ChatApplicationServer.entity.ChatBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatBoxRepo extends JpaRepository<ChatBox, Long> {

    List<ChatBox> findAllByParticipants_Id(Long participantId);

}
