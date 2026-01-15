package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.ChatMessage;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatHistoryService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage saveChat(String userMessage, String aiResponse, Project project) {
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setUserMessage(userMessage);
        chatMessage.setAiResponse(aiResponse);
        chatMessage.setProject(project);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(Long projectId) {

        return chatMessageRepository.findProjectByIdOrderByTimestampAsc(projectId);
    }
}
