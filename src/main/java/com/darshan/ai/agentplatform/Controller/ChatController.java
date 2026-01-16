package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.ChatRequest;
import com.darshan.ai.agentplatform.Entity.ChatMessage;
import com.darshan.ai.agentplatform.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/{projectId}")
    public ResponseEntity<String> chat(
            @PathVariable Long projectId,
            @RequestBody ChatRequest request
    ) {
        String response = chatService.chatWithProjects(
                projectId,
                request.getMessage()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String testChat() {
        return "Chat access working";
    }

}
