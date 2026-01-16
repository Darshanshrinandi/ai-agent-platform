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

//    public String getChatResponse(String prompt) {
//
//        System.out.println("🔥 OPENROUTER_API_KEY = [" + apiKey + "]");
//
//        Map<String, String> message = new HashMap<>();
//        message.put("role", "user");
//        message.put("content", prompt);
//
//        List<Map<String, String>> messages = new ArrayList<>();
//        messages.add(message);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", "meta-llama/llama-3-8b-instruct");
//        requestBody.put("messages", messages);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//        headers.set("HTTP-Referer", "https://ai-agent-platform-production-8f25.up.railway.app");
//        headers.set("X-Title", "AI-Agent-Platform");
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(
//                OPENROUTER_URL,
//                entity,
//                Map.class
//        );
//
//        Map body = response.getBody();
//        if (body == null) return "No response from OpenRouter";
//
//        List<Map<String, Object>> choices = (List<Map<String, Object>>) body.get("choices");
//        Map<String, Object> firstChoice = choices.get(0);
//        Map<String, String> messageResponse = (Map<String, String>) firstChoice.get("message");
//
//        return messageResponse.get("content");
//    }

    public String getChatResponse(String prompt) {

        System.out.println("🔥 OPENROUTER_API_KEY = [" + apiKey + "]");
        System.out.println("🔥 LENGTH = " + (apiKey == null ? "null" : apiKey.length()));

        // ---------- Request Body ----------
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "mistralai/mistral-7b-instruct"); // ✅ safer model
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        // ---------- Headers ----------
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.set("HTTP-Referer", "https://ai-agent-platform-production-8f25.up.railway.app");
        headers.set("X-Title", "AI-Agent-Platform");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    OPENROUTER_URL,
                    entity,
                    Map.class
            );

            Map body = response.getBody();
            if (body == null) {
                return "No response body from OpenRouter";
            }

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) body.get("choices");

            if (choices == null || choices.isEmpty()) {
                return "No choices returned from OpenRouter";
            }

            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> message =
                    (Map<String, Object>) firstChoice.get("message");

            return message.get("content").toString();

        } catch (HttpClientErrorException e) {
            // 🔥 THIS WILL SHOW THE REAL OPENROUTER ERROR
            System.err.println("❌ OpenRouter error status: " + e.getStatusCode());
            System.err.println("❌ OpenRouter error body: " + e.getResponseBodyAsString());
            return "OpenRouter Error: " + e.getStatusCode();

        } catch (Exception e) {
            e.printStackTrace();
            return "Unexpected error while calling OpenRouter";
        }
    }


}
