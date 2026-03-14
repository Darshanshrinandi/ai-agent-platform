package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.CreateProjectRequest;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody CreateProjectRequest request,
            Authentication authentication) {

        Project project = projectService.createProject(
                request.getName(),
                request.getDescription(),
                authentication.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Project>> getMyProjects(Authentication authentication) {

        List<Project> projects =
                projectService.getProjectsByUser(authentication.getName());

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(
            @PathVariable Long projectId,
            Authentication authentication) {

        Project project =
                projectService.getProject(projectId, authentication.getName());

        return ResponseEntity.ok(project);

    }

    @DeleteMapping("/projectId")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId,Authentication authentication) {

         projectService.deleteProject(projectId, authentication.getName());
         return ResponseEntity.noContent().build();
    }
}