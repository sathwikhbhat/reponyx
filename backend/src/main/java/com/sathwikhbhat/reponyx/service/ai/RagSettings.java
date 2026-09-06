package com.sathwikhbhat.reponyx.service.ai;

public final class RagSettings {

    public static final int TOP_K_CHUNKS = 8;

    public static final long STREAM_TIMEOUT_MS = 180_000L;

    public static final String METADATA_REPO_ID = "repoId";

    private RagSettings() {
        /* This utility class should not be instantiated */
    }
}
