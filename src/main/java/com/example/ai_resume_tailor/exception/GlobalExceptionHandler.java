package com.example.ai_resume_tailor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Map<String, String>> openRouterUnauthorized(HttpClientErrorException.Unauthorized ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of(
                "error", "OpenRouter returned 401 Unauthorized (invalid or missing API key).",
                "hint", "Set a valid key via OPENROUTER_API_KEY or openrouter.api.key. Create/rotate keys at https://openrouter.ai/settings/keys"
        ));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, String>> openRouterClientError(HttpClientErrorException ex) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) {
            status = HttpStatus.BAD_GATEWAY;
        }
        return ResponseEntity.status(status.is4xxClientError() ? HttpStatus.BAD_GATEWAY : status).body(Map.of(
                "error", "Upstream AI request failed: " + ex.getStatusCode()
        ));
    }
}