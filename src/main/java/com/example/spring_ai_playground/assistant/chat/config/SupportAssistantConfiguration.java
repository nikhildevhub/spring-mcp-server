package com.example.spring_ai_playground.assistant.chat.config;

import com.example.spring_ai_playground.assistant.chat.tools.DateTimeTool;
import com.example.spring_ai_playground.assistant.chat.tools.TicketTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupportAssistantConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public ToolCallbackProvider mcpToolProvider(DateTimeTool dateTimeTool, TicketTool ticketTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(dateTimeTool, ticketTool)
                .build();
    }
}
