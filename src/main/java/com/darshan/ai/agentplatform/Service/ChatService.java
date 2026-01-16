package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String chatWithProjects(Long projectId, String userMessage) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));

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
