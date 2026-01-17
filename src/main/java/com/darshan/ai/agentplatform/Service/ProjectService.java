package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;
import com.darshan.ai.agentplatform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;


    public Project createProject(String name, String description, User user) {

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUser(user); // already verified

        return projectRepository.save(project);
    }


    public List<Project> getProjectByUser(Long userId) {
        return projectRepository.findByUserId(userId);
    }



    public Project getProjectById(Long projectId){

        return projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("project not found"));
    }

}
