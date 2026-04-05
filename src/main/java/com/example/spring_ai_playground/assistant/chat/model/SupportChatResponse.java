package com.example.spring_ai_playground.assistant.chat.model;

import com.example.spring_ai_playground.assistant.chat.enums.ChatResponseCategory;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record SupportChatResponse(
        // @JsonPropertyDescription annotations help the AI understand what each field should contain.
        @JsonPropertyDescription("The category of the support question: TECHNICAL, BILLING, SECURITY, UPGRADE, or GENERAL")
        ChatResponseCategory category,

        @JsonPropertyDescription("The helpful answer to the customer's question")
        String answer
) {}
