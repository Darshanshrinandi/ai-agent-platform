package com.darshan.ai.agentplatform.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OpenAiService {

    @Value("${openrouter.api.key}")
    private String apiKey;


    private final String OPENROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String prompt) {


        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(message);


        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model", "meta-llama/llama-3-8b-instruct");
        requestBody.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "http://localhost");
        headers.set("X-Title", "AI-Agent-Platform");

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        // Call OpenRouter
        ResponseEntity<Map> response = restTemplate.postForEntity(
                OPENROUTER_URL,
                entity,
                Map.class
        );


        Map body = response.getBody();
        if (body == null) return "No response from OpenRouter";

        List<Map<String, Object>> choices = (List<Map<String, Object>>) body.get("choices");
        Map<String, Object> firstChoice = choices.get(0);
        Map<String, String> messageResponse = (Map<String, String>) firstChoice.get("message");

        return messageResponse.get("content");
    }
}
