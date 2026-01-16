package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;
import com.darshan.ai.agentplatform.Repository.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private PromptService promptService;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ChatHistoryService chatHistoryService;

    @Autowired
    private PromptRepository promptRepository;

    public String chatWithProjects(Long projectId, String userMessage) throws AccessDeniedException {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Project project = projectRepository.findByIdAndUserEmail(projectId, email)
                .orElseThrow(() -> new AccessDeniedException("You are not allowed to access this project"));

        List<Prompt> prompts = promptService.getPromptsByProject(projectId);

        StringBuilder context = new StringBuilder();

        for (Prompt prompt : prompts) {
            context.append(prompt.getContent()).append("\n");
        }

        String finalPrompt = context + "\nUser: " + userMessage;

        String aiResponse = openAiService.getChatResponse(finalPrompt);

        chatHistoryService.saveChat(userMessage, aiResponse, project);
        return aiResponse;

    }


}
