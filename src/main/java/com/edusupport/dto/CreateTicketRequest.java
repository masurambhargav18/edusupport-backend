package com.edusupport.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.edusupport.entity.Priority;
public record CreateTicketRequest(
    @NotNull Long studentId,
    @NotBlank String category,
    @NotBlank String subject,
    @NotBlank String description,
    @NotNull Priority priority
) {}
