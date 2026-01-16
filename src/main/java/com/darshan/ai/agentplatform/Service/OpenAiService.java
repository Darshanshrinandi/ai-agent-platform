package com.darshan.ai.agentplatform.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

    private static final String OPENROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String prompt) {

        // ---------- Request Body ----------
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "mistralai/mistral-7b-instruct");
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        // ---------- Headers ----------
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.set("HTTP-Referer", "https://ai-agent-platform-production-8f25.up.railway.app");
        headers.set("X-Title", "AI-Agent-Platform");

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    OPENROUTER_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                return "OpenRouter returned non-success status: "
                        + response.getStatusCode();
            }

            Map<String, Object> body = response.getBody();
            if (body == null) {
                return "No response body from OpenRouter";
            }

            Object choicesObj = body.get("choices");
            if (!(choicesObj instanceof List<?> choices) || choices.isEmpty()) {
                return "No choices returned from OpenRouter";
            }

            Object firstChoiceObj = choices.get(0);
            if (!(firstChoiceObj instanceof Map<?, ?> firstChoice)) {
                return "Invalid OpenRouter response format (choice)";
            }

            Object messageObj = firstChoice.get("message");
            if (!(messageObj instanceof Map<?, ?> message)) {
                return "Invalid OpenRouter response format (message)";
            }

            Object content = message.get("content");
            return content != null ? content.toString() : "Empty AI response";

        }
        catch (HttpClientErrorException e) {

            System.err.println(" OpenRouter HTTP error: " + e.getStatusCode());
            System.err.println(" Response body: " + e.getResponseBodyAsString());
            return "OpenRouter Error: " + e.getStatusCode();

        }
        catch (Exception e) {
            e.printStackTrace();
            return "Unexpected error while calling OpenRouter";
        }
    }



}
