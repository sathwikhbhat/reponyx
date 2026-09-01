package com.sathwikhbhat.reponyx.service.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubRateLimiter {

    private final long delayMs;

    public GithubRateLimiter(@Value("${app.github.api-delay-ms}") long delayMs) {
        this.delayMs = delayMs;
    }

    public void pause() {
        if (delayMs <= 0) {
            return;
        }
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while rate limiting", e);
        }
    }
}
