package com.example.spring_ai_playground.assistant.chat.model;

public record TicketResult(
        String ticketId,
        String summary,
        String category,
        String priority,
        String status,
        String createdAt,
        String message
) {}
