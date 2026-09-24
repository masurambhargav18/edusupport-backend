package com.edusupport.dto;
import jakarta.validation.constraints.NotNull;
import com.edusupport.entity.Priority;
import com.edusupport.entity.TicketStatus;
public record UpdateTicketRequest(
    @NotNull TicketStatus status,
    @NotNull Priority priority,
    Long assignedToId
) {}
