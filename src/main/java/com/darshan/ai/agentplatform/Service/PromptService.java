package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import com.darshan.ai.agentplatform.Repository.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {


    @Autowired
    private PromptRepository promptRepository;

    public Prompt addPrompt(String content, Project project) {

        Prompt prompt = new Prompt();
        prompt.setContent(content);
        prompt.setProject(project);

        return promptRepository.save(prompt);
    }

    public List<Prompt> getPromptsByProject(Long projectId) {
        return promptRepository.findByProjectId(projectId);
    }
}
