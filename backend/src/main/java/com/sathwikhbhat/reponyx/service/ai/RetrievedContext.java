package com.sathwikhbhat.reponyx.service.ai;

import com.sathwikhbhat.reponyx.dto.CitationDTO;

import java.util.List;

public record RetrievedContext(List<CitationDTO> citations, String contextText) {
}
