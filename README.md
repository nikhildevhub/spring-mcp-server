# Spring MCP Server

This project is a demo for Spring Boot, containing several examples to explore Spring AI features and finally exposing some tools as an MCP server.

## Prerequisites

- Java 25
- Maven
- Ollama (for AI model interactions)

## Running the Application

```bash
./mvnw spring-boot:run
```

## Features

- Chat services with various controllers (e.g., ChatController, PromptTemplateChatController)
- Vector store integration for knowledge retrieval
- Custom tools (DateTimeTool, TicketTool)
- MCP server setup for exposing AI tools
- Data persistence with H2 database

## Project Structure

- `src/main/java/com/example/spring_ai_playground/`: Main application code
  - `assistant/chat/`: Chat-related components
    - `config/`: Configuration classes
    - `controller/`: REST controllers
    - `service/`: Business logic services
    - `tools/`: Custom AI tools
    - `knowledge/`: Document loading and knowledge services
- `src/main/resources/`: Application properties, data files, and prompts
- `src/test/`: Test classes</content>
<parameter name="filePath">/Users/nikhilaggarwal/Devbox/IdeaProjects/spring-mcp-server/README.md
