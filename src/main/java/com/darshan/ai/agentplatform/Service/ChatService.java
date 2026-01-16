package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;
import com.darshan.ai.agentplatform.Repository.PromptRepository;
import com.darshan.ai.agentplatform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;

    @Autowired
    private PromptRepository promptRepository;

    public String chatWithProjects(Long projectId, String userMessage) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findByIdAndUserId(projectId, user.getId()).orElseThrow(() -> new RuntimeException("Project not found"));

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
