package com.darshan.ai.agentplatform.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePromptRequest {

    @NotBlank(message = "Prompt content is required")
    @Size(max = 2000, message = "Prompt must be less than 2000 characters")
    private String content;
}
