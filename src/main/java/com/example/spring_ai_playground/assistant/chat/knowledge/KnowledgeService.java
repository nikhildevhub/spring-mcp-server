package com.example.spring_ai_playground.assistant.chat.knowledge;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public KnowledgeService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String answerQuestion(String question) {
        SearchRequest ragSearchRequest = SearchRequest.builder().topK(3).build();

        QuestionAnswerAdvisor ragAdvisor = QuestionAnswerAdvisor
                .builder(vectorStore)
                .searchRequest(ragSearchRequest)
                .build();

        return chatClient.prompt()
                .user(question)
                .advisors(ragAdvisor)
                .call()
                .content();
    }
}
