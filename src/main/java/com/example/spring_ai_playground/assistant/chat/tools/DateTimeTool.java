package com.example.spring_ai_playground.assistant.chat.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTool {

    private static final Logger log = LoggerFactory.getLogger(DateTimeTool.class);

    // @Tool: Marks a method as callable by the AI. The description helps the AI understand when to use this tool
    // @ToolParam: Describes each parameter so the AI knows what values to provide.
    @Tool(description = "Get the current date and time in a specific timezone. Use this when the user asks about the current time or date.")
    public String getCurrentDateTime(@ToolParam(description = "The timezone, e.g., 'America/New_York', 'Europe/London', 'Asia/Tokyo'")
                                         String timezone)
    {
        log.info("Invoking DateTimeTool with timezone {}", timezone);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    }
}
