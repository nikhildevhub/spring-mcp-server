package com.example.spring_ai_playground.assistant.chat.controller;

import com.example.spring_ai_playground.assistant.chat.tools.DateTimeTool;
import com.example.spring_ai_playground.assistant.chat.tools.TicketTool;
import com.example.spring_ai_playground.assistant.chat.tools.persistence.entity.SupportTicket;
import com.example.spring_ai_playground.assistant.chat.tools.persistence.repository.SupportTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    private static final Logger log = LoggerFactory.getLogger(ToolsController.class);
    @Autowired
    private ChatModel chatModel;

    private final ChatClient chatClient;
    private final DateTimeTool dateTimeTool;
    private final TicketTool ticketTool;
    private final SupportTicketRepository supportTicketRepository;

    public ToolsController(ChatClient chatClient,
                           DateTimeTool dateTimeTool,
                           TicketTool ticketTool,
                           SupportTicketRepository supportTicketRepository) {
        this.chatClient = chatClient;
        this.dateTimeTool = dateTimeTool;
        this.ticketTool = ticketTool;
        this.supportTicketRepository = supportTicketRepository;
    }

    @GetMapping("/chat")
    public String chatWithTools(@RequestParam String message) {
        log.info("Chat Model in use : {}", chatModel.getClass());
        return chatClient.prompt()
                .system("""
                        You are the Support Assistant with access to tools.
                        Use the available tools when appropriate to help the user.
                        If a tool is available, you MUST call it.
                        Execute tool calls directly without asking for confirmation.
                        Always be helpful and provide context with your answers.
                        """)
                .user(message)
                .tools(dateTimeTool, ticketTool)
                .call()
                .content();
    }

    @GetMapping("/tickets")
    public List<SupportTicket> getAllTickets() {
        return (List<SupportTicket>) supportTicketRepository.findAll();
    }
}
