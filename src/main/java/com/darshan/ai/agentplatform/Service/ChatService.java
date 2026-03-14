package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;

import com.darshan.ai.agentplatform.Repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ChatService {

    private final PromptService promptService;
    private final OpenAiService openAiService;
    private final ProjectRepository projectRepository;
    private final ChatHistoryService chatHistoryService;

    public String chatWithProject(Long projectId, String userMessage, String email) {

        Project project = projectRepository
                .findByIdAndUserEmail(projectId, email)
                .orElseThrow(() -> new AccessDeniedException("Project not found or access denied"));

        List<Prompt> prompts = promptService.getPromptsByProject(projectId, email);

        String context = prompts.stream()
                .map(Prompt::getContent)
                .reduce("", (a, b) -> a + "\n" + b);

        String finalPrompt = context + "\nUser: " + userMessage;

        String aiResponse = openAiService.getChatResponse(finalPrompt);

        chatHistoryService.saveChat(userMessage, aiResponse, project);

        return aiResponse;
    }
}