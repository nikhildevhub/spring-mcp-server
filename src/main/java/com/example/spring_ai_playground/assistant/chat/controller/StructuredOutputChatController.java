package com.example.spring_ai_playground.assistant.chat.controller;

import com.example.spring_ai_playground.assistant.chat.model.SupportChatResponse;
import com.example.spring_ai_playground.assistant.chat.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class StructuredOutputChatController {

    private final ChatService chatService;

    public StructuredOutputChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @GetMapping("/structured")
    public SupportChatResponse detailedChat(@RequestParam String query,
                                            @RequestParam(defaultValue = "Standard") String tier) {
        return chatService.chatWithStructuredOutput(query, tier);
    }

}
