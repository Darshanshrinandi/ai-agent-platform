package com.darshan.ai.agentplatform.Repository;

import com.darshan.ai.agentplatform.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    List<ChatMessage> findProjectByIdOrderByTimestampAsc(Long projectId);

}
