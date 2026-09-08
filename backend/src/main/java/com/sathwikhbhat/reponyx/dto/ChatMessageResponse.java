package com.sathwikhbhat.reponyx.dto;

import com.sathwikhbhat.reponyx.entity.MessageRole;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChatMessageResponse(
        UUID id,
        MessageRole role,
        String content,
        List<CitationDTO> citations,
        Instant createdAt) {
}
