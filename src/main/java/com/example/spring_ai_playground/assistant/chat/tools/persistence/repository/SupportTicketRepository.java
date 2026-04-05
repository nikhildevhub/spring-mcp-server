package com.example.spring_ai_playground.assistant.chat.tools.persistence.repository;

import com.example.spring_ai_playground.assistant.chat.tools.persistence.entity.SupportTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepository extends CrudRepository<SupportTicket, Long> {
    Optional<SupportTicket> findByTicketId(String ticketId);
    List<SupportTicket> findByStatus(String status);
    List<SupportTicket> findByCategory(String category);
}
