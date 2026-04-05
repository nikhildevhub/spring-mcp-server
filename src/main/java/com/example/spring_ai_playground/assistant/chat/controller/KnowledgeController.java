package com.example.spring_ai_playground.assistant.chat.controller;

import com.example.spring_ai_playground.assistant.chat.knowledge.KnowledgeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/ask")
    public Map<String, String> askQuestion(@RequestParam String question) {
        String answer = knowledgeService.answerQuestion(question);
        return Map.of(
                "question", question,
                "answer", answer
        );
    }
}
