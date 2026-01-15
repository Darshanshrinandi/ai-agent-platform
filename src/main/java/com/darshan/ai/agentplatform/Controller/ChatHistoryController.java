package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.Entity.ChatMessage;
import com.darshan.ai.agentplatform.Service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat-history")
public class ChatHistoryController {

    @Autowired
    private ChatHistoryService chatHistoryService;

    @GetMapping("/{projectId}")
    public List<ChatMessage> getChatHistory(@PathVariable Long projectId) {
        return chatHistoryService.getChatHistory(projectId);
    }
}
