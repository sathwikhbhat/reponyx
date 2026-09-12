# Reponyx

Reponyx is a web platform that helps developers understand their GitHub repositories with the help of AI. You sign in with your GitHub account, pick a repository to index, and once indexing finishes you can ask questions about the code in plain English. Each answer is grounded in the actual code of your repository and comes with citations pointing to the exact files and the relevant sections within them.

## The problem this solves

Reading a large or unfamiliar codebase takes time. You have to figure out where things live, how modules connect, and what a given function actually does. Documentation is often missing or stale. Reponyx removes most of that legwork by letting you ask targeted questions about your code and receiving answers that show their source, so you can verify the response and jump straight to the relevant part of the code.

## How it works

Reponyx follows a retrieval-augmented generation (RAG) pipeline. It has two distinct phases.

### Indexing phase

1. In the dashboard, you see a list of repositories from your GitHub account. Pick one and start indexing.
2. Reponyx fetches the repository tree and filters out files that are not useful or too large to index, such as generated artifacts, lockfiles, and binaries.
3. The remaining files are downloaded and split into meaningfully sized chunks. Chunking happens per file, so the original location of every chunk is preserved.
4. Each chunk is converted into an embedding vector and stored in a vector database. The database also stores metadata such as the repository ID and the file path for every chunk.
5. Progress is tracked in real time, including files processed and total chunk count. Indexing runs in the background, so you can close the page and come back later.

If you index a repository again, the old vectors are removed first, which means re-indexing always reflects the current state of the repository.

### Chat phase

1. You start a chat session scoped to a single indexed repository. Chatting is only allowed once indexing has finished.
2. You ask a question. Reponyx converts your question into an embedding and searches the vector database for the chunks that are most similar to it.
3. The retrieved chunks are bundled into the prompt along with your question, so the AI model answers based on your actual code instead of general knowledge.
4. The response is streamed back to you in real time. Every part of the answer is traced back to the chunks it came from, and the sources are rendered as citations. Clicking a citation shows you the matching file and chunk.

Chat history and sessions are persisted per repository, so you can pick up a conversation later.

## Project structure

- `backend/` - Spring Boot API. Handles GitHub OAuth, repository management, indexing, and the RAG chat pipeline.
- `frontend/` - Next.js web app. Provides the dashboard, repository cards with live index status, and the chat interface with citations.
- `docker/` - Database init scripts, including the pgvector extension.
- `docker-compose.yml` - Runs the whole stack: database, backend, and frontend.

The backend is organized around distinct concerns. Repository and chat endpoints live in `controller`, indexing logic in `service/indexing`, the AI pipeline in `service/ai`, and GitHub integration in `service/github`.

## Getting started

### Configuration

Copy `.env.example` to `.env` and fill in the required values:

- `GOOGLE_GENAI_API_KEY` - key used for chat and embeddings.
- `GITHUB_CLIENT_ID` and `GITHUB_CLIENT_SECRET` - from a GitHub OAuth app you create for Reponyx.
- `APP_TOKEN_ENCRYPTOR_PASSWORD` and `APP_TOKEN_ENCRYPTOR_SALT` - used to encrypt GitHub access tokens in the database. Use long random values.

The remaining values in the file control ports and browser-facing URLs and come with sensible defaults.

### Run with Docker

```bash
docker compose up --build
```

Then open `http://localhost:3000`. The backend runs on port 8080 by default.

### Run locally

Start the database, then run each service in its own terminal.

```bash
docker compose up postgres

cd backend && ./mvnw spring-boot:run

cd frontend && npm install && npm run dev
```

## Notes

- Only users who own a repository can index it or chat with it. All repository and chat operations are scoped to the signed-in user.
- Github access tokens are stored encrypted and decrypted only when interacting with the GitHub API.
- A repository must be indexed before it can be used for chat.