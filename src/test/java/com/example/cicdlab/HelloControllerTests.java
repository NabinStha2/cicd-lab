package com.example.cicdlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test: starts the real app on a random port and hits the API.
 * This is the kind of test a CI pipeline runs on every commit.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class HelloControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloEndpointReturnsGreeting() {
        var response = restTemplate.getForEntity("http://localhost:" + port + "/api/hello", Map.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsEntry("message", "Hello, CI/CD!");
    }

    @Test
    void helloNameEndpointPersonalizesGreeting() {
        var response = restTemplate.getForEntity("http://localhost:" + port + "/api/hello/Nabin", Map.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsEntry("message", "Hello, Nabin!");
    }
}
