package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.CreateProjectRequest;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Service.ProjectService;
import com.darshan.ai.agentplatform.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody CreateProjectRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(
                        request.getName(),
                        request.getDescription()
                ));
    }



    @GetMapping("/getProject/{userId}")
    public ResponseEntity<Project> getProject(@PathVariable("userId") Long userId) {

        Project project = projectService.getProjectById(userId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
