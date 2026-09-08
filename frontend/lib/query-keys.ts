export const queryKeys = {
    auth: {
        all: ["auth"] as const,
        me: () => [...queryKeys.auth.all, "me"] as const,
    },
    repos: {
        all: ["repos"] as const,
        list: () => [...queryKeys.repos.all, "list"] as const,
        detail: (repoId: string) => [...queryKeys.repos.all, "detail", repoId] as const,
        status: (repoId: string) => [...queryKeys.repos.all, "status", repoId] as const,
    },
    chat: {
        all: ["chat"] as const,
        sessions: (repositoryId: string) =>
            [...queryKeys.chat.all, "sessions", repositoryId] as const,
        messages: (sessionId: string) =>
            [...queryKeys.chat.all, "messages", sessionId] as const,
    },
};
