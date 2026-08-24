package com.sathwikhbhat.reponyx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private Long githubId;

    @Column(unique = true, nullable = false)
    private String githubUsername;

    @Column(nullable = false)
    private String displayName;

    private String avatarUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accessToken;

    private String tokenScope;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @PrePersist
    void onCreate() {
        if(createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
