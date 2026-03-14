package com.darshan.ai.agentplatform.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    @Value("${openrouter.api.key}")
    private String apiKey;


    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String prompt) {

        System.out.println(">>> API KEY: [" + apiKey + "]");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.1-8b-instant");
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey.trim());



        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    GROQ_URL, HttpMethod.POST, entity, Map.class
            );

            Map<String, Object> body = response.getBody();
            if (body == null) return "No response body from Groq";

            Object choicesObj = body.get("choices");
            if (!(choicesObj instanceof List<?> choices) || choices.isEmpty())
                return "No choices returned from Groq";

            Object firstChoiceObj = choices.get(0);
            if (!(firstChoiceObj instanceof Map<?, ?> firstChoice))
                return "Invalid response format (choice)";

            Object messageObj = firstChoice.get("message");
            if (!(messageObj instanceof Map<?, ?> message))
                return "Invalid response format (message)";

            Object content = message.get("content");
            return content != null ? content.toString() : "Empty AI response";

        } catch (HttpClientErrorException e) {
            System.err.println("Groq HTTP error: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
            return "AI Error: " + e.getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unexpected error: " + e.getMessage();
        }
    }
}