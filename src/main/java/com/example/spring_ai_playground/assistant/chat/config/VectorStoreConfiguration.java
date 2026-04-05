package com.example.spring_ai_playground.assistant.chat.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfiguration {

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        // The vector store requires an embedding model to convert text into vectors
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}
