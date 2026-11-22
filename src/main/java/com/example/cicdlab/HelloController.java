package com.example.cicdlab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

/**
 * A tiny REST controller so our pipeline has something real to test.
 * Endpoints:
 *   GET /api/hello          -> greeting + server time
 *   GET /api/hello/{name}   -> personalized greeting
 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello, CI/CD!",
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/api/hello/{name}")
    public Map<String, String> helloName(@PathVariable String name) {
        return Map.of(
                "message", "Hello, " + name + "!",
                "timestamp", Instant.now().toString()
        );
    }
}
