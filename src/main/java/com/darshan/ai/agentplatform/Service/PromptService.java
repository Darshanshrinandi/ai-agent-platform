package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;
import com.darshan.ai.agentplatform.Repository.PromptRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;
    private final ProjectRepository projectRepository;

    public Prompt addPrompt(Long projectId, String content, String email) {

        Project project = projectRepository
                .findByIdAndUserEmail(projectId, email)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Prompt prompt = new Prompt();
        prompt.setContent(content);
        prompt.setProject(project);

        return promptRepository.save(prompt);
    }

    public List<Prompt> getPromptsByProject(Long projectId, String email) {

        Project project = projectRepository
                .findByIdAndUserEmail(projectId, email)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return promptRepository.findByProject(project);
    }
}