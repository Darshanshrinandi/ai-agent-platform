package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.CreateProjectRequest;
import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Service.ProjectService;
import com.darshan.ai.agentplatform.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody CreateProjectRequest request) {

        Project project = projectService.createProject(
                request.getName(),
                request.getDescription()
        );

        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }


    @GetMapping("/getProject/{userId}")
    public ResponseEntity<Project> getProject(@PathVariable("userId") Long userId) {

        Project project = projectService.getProjectById(userId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
