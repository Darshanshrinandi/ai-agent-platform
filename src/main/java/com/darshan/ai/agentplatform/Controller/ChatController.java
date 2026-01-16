package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/{projectId}")
    public String chat(@PathVariable Long projectId, @RequestBody String userMessage) {


        return chatService.chatWithProjects(projectId, userMessage);
    }

    @GetMapping("/test")
    public String testChat() {
        return "Chat access working";
    }

}
