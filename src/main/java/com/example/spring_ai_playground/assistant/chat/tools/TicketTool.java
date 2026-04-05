package com.example.spring_ai_playground.assistant.chat.tools;

import com.example.spring_ai_playground.assistant.chat.model.TicketResult;
import com.example.spring_ai_playground.assistant.chat.tools.persistence.entity.SupportTicket;
import com.example.spring_ai_playground.assistant.chat.tools.persistence.repository.SupportTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TicketTool {

    private static final Logger log = LoggerFactory.getLogger(TicketTool.class);
    private final SupportTicketRepository supportTicketRepository;
    private final AtomicInteger ticketCounter = new AtomicInteger(1000);

    public TicketTool(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    @Tool(description = "Create a new support ticket. Use this when the user explicitly requests to create, open, or file a support ticket.")
    public TicketResult createTicket(@ToolParam(description = "Brief summary of the issue (max 100 chars)")
                                     String summary,
                                     @ToolParam(description = "Category: TECHNICAL, BILLING, SECURITY, UPGRADE, or GENERAL")
                                     String category,
                                     @ToolParam(description = "Priority: LOW, MEDIUM, HIGH, or CRITICAL")
                                     String priority) {
        log.info("Invoking the ticket tool : createTicket with summary : {}, category : {} and priority : {}",
                summary, category, priority);
        String ticketId = "TSE-" + ticketCounter.incrementAndGet();
        SupportTicket ticket = new SupportTicket(ticketId, summary, category.toUpperCase(), priority.toUpperCase());
        SupportTicket saved = supportTicketRepository.save(ticket);

        return new TicketResult(
                saved.ticketId(),
                saved.summary(),
                saved.category(),
                saved.priority(),
                saved.status(),
                saved.createdAt().toString(),
                "Ticket created successfully"
        );
    }

    @Tool(description = "List all open support tickets. Use this when the user wants to see their tickets or check ticket status.")
    public List<SupportTicket> listOpenTickets() {
        log.info("Invoking Ticket Tool : listOpenTickets");
        return supportTicketRepository.findByStatus("OPEN");
    }

    public record TicketResult(
            String ticketId,
            String summary,
            String category,
            String priority,
            String status,
            String createdAt,
            String message
    ) {}
}
