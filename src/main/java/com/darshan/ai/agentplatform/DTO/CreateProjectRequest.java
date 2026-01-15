package com.darshan.ai.agentplatform.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProjectRequest {

    @NotBlank(message = "project name is required")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
}
