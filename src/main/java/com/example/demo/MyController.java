package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    @GetMapping("/response-entity")
    public Mono<ResponseEntity<String>> usingResponseEntityBuilder() {
        String responseHeaderKey = "Baeldung-Example-Header";
        String responseHeaderValue = "Value-ResponseEntityBuilder";
        String responseBody = "Response with header using ResponseEntity (builder)";

        return Mono.just(ResponseEntity.ok()
            .header(responseHeaderKey, responseHeaderValue)
            .body(responseBody));
    }
}
