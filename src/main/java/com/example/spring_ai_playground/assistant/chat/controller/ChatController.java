package com.example.spring_ai_playground.assistant.chat.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/simple")
    public String simpleChat(@RequestParam(defaultValue = "What is 2 + 2") String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }

    @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> streamChat(@RequestParam(defaultValue = "What is 2 + 2? Explain") String query) {
        return chatClient.prompt()
                .user(query)
                .stream()
                .content();
    }

    @GetMapping("/detailed")
    public ChatResponse detailedChat(@RequestParam(defaultValue = "What is 2 + 2?") String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .chatResponse();
    }

}
