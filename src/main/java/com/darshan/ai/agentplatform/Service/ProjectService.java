package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.Project;

import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Repository.ProjectRepository;

import com.darshan.ai.agentplatform.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(String name, String description, String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUser(user);

        return projectRepository.save(project);
    }

    public List<Project> getProjectsByUser(String email) {

        return projectRepository.findByUserEmail(email);
    }

    public Project getProject(Long projectId, String email) {

        return projectRepository.findByIdAndUserEmail(projectId, email).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public void deleteProject(Long projectId, String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Project project = projectRepository.findByIdAndUserId(projectId, user.getId()).orElseThrow(() -> new AccessDeniedException("Project not found by this user"));

        projectRepository.delete(project);

    }
}