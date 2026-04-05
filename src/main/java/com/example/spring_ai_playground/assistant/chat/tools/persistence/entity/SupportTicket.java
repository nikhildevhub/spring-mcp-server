package com.example.spring_ai_playground.assistant.chat.tools.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("support_ticket")
public record SupportTicket(
        @Id Long id,
        String ticketId,
        String summary,
        String category,
        String priority,
        String status,
        LocalDateTime createdAt
) {
    public SupportTicket(String ticketId, String summary, String category, String priority) {
        this(null, ticketId, summary, category, priority, "OPEN", LocalDateTime.now());
    }
}
