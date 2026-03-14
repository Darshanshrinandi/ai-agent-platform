package com.darshan.ai.agentplatform.Controller;
import com.darshan.ai.agentplatform.DTO.ChatRequest;
import com.darshan.ai.agentplatform.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/{projectId}")
    public ResponseEntity<String> chat(
            @PathVariable Long projectId,
            @RequestBody ChatRequest request,
            Authentication authentication
    ) {

        String response = chatService.chatWithProject(
                projectId,
                request.getMessage(),
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testChat() {
        return ResponseEntity.ok("Chat access working");
    }
}
