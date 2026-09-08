package com.sathwikhbhat.reponyx.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateChatSessionRequest(@NotNull UUID repositoryId, String title) {
}
