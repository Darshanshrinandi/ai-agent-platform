package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.CreatePromptRequest;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Service.ProjectService;
import com.darshan.ai.agentplatform.Service.PromptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/prompts")
public class PromptController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PromptService promptService;

    @PostMapping
    public Prompt addPrompt(@PathVariable Long projectId, @Valid @RequestBody CreatePromptRequest promptRequest) {

        Project project = projectService.getProjectById(projectId);

        return promptService.addPrompt(promptRequest.getContent(), project);
    }

    @GetMapping("/allPrompts")
    public List<Prompt> getAllPrompts(@PathVariable Long projectId) {

        return promptService.getPromptsByProject(projectId);
    }
}
