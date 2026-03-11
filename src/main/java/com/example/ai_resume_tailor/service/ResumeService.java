package com.example.ai_resume_tailor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class ResumeService {

    private final String API_KEY = "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";

    public String analyzeResume(String resumeText, String jobDescription) throws JsonProcessingException {

        String prompt = """
You are an ATS resume analyzer.

Analyze the resume against the job description and return ONLY JSON in this format:

{
  "atsScore": number between 0 and 100,
  "missingSkills": ["skill1","skill2"],
  "suggestions": ["suggestion1","suggestion2"],
  "improvedResume": "rewritten resume aligned to job description"
}

Resume:
%s

Job Description:
%s
""".formatted(resumeText, jobDescription);
        String url = "https://openrouter.ai/api/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = Map.of(
                "model", "openai/gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        String result = root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        return result;
    }
}