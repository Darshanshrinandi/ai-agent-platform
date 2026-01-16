package com.darshan.ai.agentplatform.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequest {

    @NotBlank(message = "Message can not be empty")
    private String message;
}
