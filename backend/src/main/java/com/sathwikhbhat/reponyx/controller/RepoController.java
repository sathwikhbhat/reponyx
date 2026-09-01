package com.sathwikhbhat.reponyx.controller;

import com.sathwikhbhat.reponyx.dto.IndexStatusResponse;
import com.sathwikhbhat.reponyx.dto.RepositoryResponse;
import com.sathwikhbhat.reponyx.entity.Repository;
import com.sathwikhbhat.reponyx.security.CurrentUser;
import com.sathwikhbhat.reponyx.service.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/repos")
@RequiredArgsConstructor
public class RepoController {

    private final CurrentUser currentUser;
    private final RepoService repoService;

    @GetMapping
    public List<RepositoryResponse> list(@RequestParam(defaultValue = "true") boolean refresh) {
        UUID userId = currentUser.require().getId();
        if (refresh) {
            return repoService.syncAndListRepos(userId);
        }
        return repoService.listStored(userId);
    }

    @GetMapping("/{id}")
    public RepositoryResponse get(@PathVariable UUID id) {
        UUID userId = currentUser.require().getId();
        return repoService.toResponse(repoService.requireOwned(id, userId));
    }

    @GetMapping("/{id}/status")
    public IndexStatusResponse status(@PathVariable UUID id) {
        UUID userId = currentUser.require().getId();
        return repoService.status(id, userId);
    }

}
