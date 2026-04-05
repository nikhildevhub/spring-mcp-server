package com.example.spring_ai_playground.assistant.chat.service;

import com.example.spring_ai_playground.assistant.chat.model.SupportChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/support-system.st")
    private Resource systemPromptResource;

    @Value("classpath:/prompts/topic-classifier.st")
    private Resource classifierPromptResource;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userQuery, String customerTier) {
        return chatClient.prompt()
                .system(sys -> sys
                        .text(systemPromptResource)
                        .param("customerTier", customerTier))
                .user(userQuery)
                .call()
                .content();
    }

    public String chatWithClassifierPrompt(String userQuery, String customerTier) {
        return chatClient.prompt()
                .system(sys -> sys
                        .text(classifierPromptResource)
                        .param("customerTier", customerTier))
                .user(userQuery)
                .call()
                .content();
    }

    public SupportChatResponse chatWithStructuredOutput(String userQuery, String customerTier) {
        return chatClient.prompt()
                .system(sys -> sys
                        .text(classifierPromptResource)
                        .param("customerTier", customerTier))
                .user(userQuery)
                .call()
                .entity(SupportChatResponse.class);
    }


}
