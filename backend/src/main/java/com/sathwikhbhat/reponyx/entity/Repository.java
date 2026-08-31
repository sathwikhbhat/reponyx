package com.sathwikhbhat.reponyx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "repositories", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "github_repo_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long githubRepoId;

    @Column(nullable = false, length = 100)
    private String owner;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 300)
    private String fullName;

    @Column(nullable = false)
    private boolean isPrivate;

    @Column(nullable = false, length = 100)
    private String defaultBranch;

    @Column(length = 100)
    private String language;

    @Column(length = 500)
    private String htmlUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private IndexStatus indexStatus = IndexStatus.PENDING;

    private Instant indexedAt;

    @Builder.Default
    private int chunkCount = 0;

    @Builder.Default
    private int filesTotal = 0;

    @Builder.Default
    private int filesProcessed = 0;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}
