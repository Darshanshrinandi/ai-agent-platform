package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.CreatePromptRequest;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Service.ProjectService;
import com.darshan.ai.agentplatform.Service.PromptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/projects/{projectId}/prompts")
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Prompt> addPrompt(
            @PathVariable Long projectId,
            @Valid @RequestBody CreatePromptRequest request,
            Authentication authentication) {

        Prompt prompt = promptService.addPrompt(
                projectId,
                request.getContent(),
                authentication.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(prompt);
    }

    @GetMapping
    public ResponseEntity<List<Prompt>> getAllPrompts(
            @PathVariable Long projectId,
            Authentication authentication) {

        List<Prompt> prompts =
                promptService.getPromptsByProject(projectId, authentication.getName());

        return ResponseEntity.ok(prompts);
    }
}