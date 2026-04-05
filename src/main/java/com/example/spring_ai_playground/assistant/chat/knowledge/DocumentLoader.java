package com.example.spring_ai_playground.assistant.chat.knowledge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Component
public class DocumentLoader {

    private static final Logger log = LoggerFactory.getLogger(DocumentLoader.class);

    private final VectorStore vectorStore;

    @Value("classpath:data/*.md")
    private Resource[] knowledgeFiles;

    public DocumentLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadDocuments() {
        MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder().build();

        List<Document> allDocs = new ArrayList<>();
        for (Resource resource : knowledgeFiles) {
            MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
            allDocs.addAll(reader.get());
        }

        /*MarkdownDocumentReader documentReader = new MarkdownDocumentReader(Arrays.asList(knowledgeFiles), config);
        List<Document> documents = new TokenTextSplitter().apply(documentReader.get());
        vectorStore.add(documents);*/

        TextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.apply(allDocs);

        vectorStore.add(chunks);

        log.info("Loaded {} document chunks into vector store", chunks.size());
    }
}
