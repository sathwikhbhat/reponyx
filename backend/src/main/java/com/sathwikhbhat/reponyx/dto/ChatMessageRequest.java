package com.sathwikhbhat.reponyx.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatMessageRequest(@NotBlank String content) {
}
